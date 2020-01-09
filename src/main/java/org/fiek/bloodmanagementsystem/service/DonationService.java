package org.fiek.bloodmanagementsystem.service;

import org.fiek.bloodmanagementsystem.common.AbstractService;
import org.fiek.bloodmanagementsystem.common.ResponseResult;
import org.fiek.bloodmanagementsystem.entity.*;
import org.fiek.bloodmanagementsystem.model.*;
import org.fiek.bloodmanagementsystem.repository.*;
import org.fiek.bloodmanagementsystem.type.ResponseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class DonationService extends AbstractService {

    @Autowired
    private CampRepository campRepository;

    @Autowired
    private DonationRepository donationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BloodGroupRepository bloodGroupRepository;

    @Autowired
    private BloodBankRepository bloodBankRepository;

    public ResponseResult createDonation(DonationRegister donationRegister){
        ResponseResult responseResult = new ResponseResult();

        responseResult.setMessage(ResponseStatus.SUCCESS.getMsg());
        responseResult.setStatus(ResponseStatus.SUCCESS.getStatusCode());
        responseResult.setResponseStatus(ResponseStatus.SUCCESS);

        try {
            Donation donation = donationRegister.getDonation();
            donation.setDonatedDate(new Date());

            Optional<Camp> camp = campRepository.findById(donationRegister.getCampId());
            donation.setCamp(camp.get());

            Optional<User> user = userRepository.findById(donationRegister.getUserId());
            donation.setUser(user.get());

            donation.setGroup(user.get().getDonatorDetails().getGroup());

            donationRepository.save(donation);
            addBloodInBank(donationRegister.getQuantity(), user.get().getDonatorDetails().getGroup());

        } catch (Exception e){
            System.err.println(e.getMessage());
            responseResult.setResponseStatus(ResponseStatus.INTERNAL_SERVER_ERROR);
            responseResult.setStatus(ResponseStatus.INTERNAL_SERVER_ERROR.getStatusCode());
            responseResult.setMessage(ResponseStatus.INTERNAL_SERVER_ERROR.getMsg());
        }

        return responseResult;
    }

    public ResponseResult updateDonation(DonationUpdate donationUpdate) {
        ResponseResult result = new ResponseResult();

        result.setResponseStatus(ResponseStatus.CONFLICT);


        Optional<Donation> donationOptional = donationRepository.findById(donationUpdate.getDonationId());

        try {
            Donation donation = donationOptional.get();
            donation.setDetails(donationUpdate.getDetails());
            donation.setQuantity(donationUpdate.getQuantity());

            Optional<Camp> camp = campRepository.findById(donationUpdate.getCampId());
            donation.setCamp(camp.get());

            Optional<User> user = userRepository.findById(donationUpdate.getUserId());
            donation.setUser(user.get());

            Optional<BloodGroup> group = bloodGroupRepository.findById(donationUpdate.getGroupId());
            donation.setGroup(group.get());

            donationRepository.save(donation);

            result.setResponseStatus(ResponseStatus.SUCCESS);
            result.setStatus(ResponseStatus.SUCCESS.getStatusCode());
            result.setMessage(ResponseStatus.SUCCESS.getMsg());

        } catch (Exception e) {
            System.err.println(e.getMessage());
            result.setResponseStatus(ResponseStatus.INTERNAL_SERVER_ERROR);
            result.setStatus(ResponseStatus.INTERNAL_SERVER_ERROR.getStatusCode());
            result.setMessage(ResponseStatus.INTERNAL_SERVER_ERROR.getMsg());
        }
        return result;
    }

    protected void addBloodInBank(Double quantity, BloodGroup group) {
        Optional<BloodBank> optionalBloodBank = bloodBankRepository.findByGroup(group);
        if (optionalBloodBank.isPresent()) {
            BloodBank bloodBank = optionalBloodBank.get();
            bloodBank.setQuantity(bloodBank.getQuantity() + quantity);
            bloodBankRepository.save(bloodBank);
        } else{
            BloodBank bloodBank = new BloodBank(quantity, group);
            bloodBankRepository.save(bloodBank);
        }
    }
}

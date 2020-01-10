package org.fiek.bloodmanagementsystem.service;

import org.fiek.bloodmanagementsystem.common.AbstractService;
import org.fiek.bloodmanagementsystem.common.DataResultList;
import org.fiek.bloodmanagementsystem.common.ResponseResult;
import org.fiek.bloodmanagementsystem.entity.*;
import org.fiek.bloodmanagementsystem.model.*;
import org.fiek.bloodmanagementsystem.repository.*;
import org.fiek.bloodmanagementsystem.type.ResponseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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

    public DataResultList<UserDonations> getAllByUser(Long userId){
        DataResultList<UserDonations> dataResultList = new DataResultList<>();
        dataResultList.setResponseStatus(ResponseStatus.SUCCESS);
        dataResultList.setStatus(ResponseStatus.SUCCESS.getStatusCode());

        try {
            List<Donation> donations = donationRepository.findAllByUserId(userId);

            dataResultList.setData(getUserDonationList(donations));

        } catch (Exception e) {
            dataResultList.setStatus(ResponseStatus.INTERNAL_SERVER_ERROR.getStatusCode());
            dataResultList.setResponseStatus(ResponseStatus.INTERNAL_SERVER_ERROR);        }

        return dataResultList;
    }

    public DataResultList<DonationData> getAllDonation(){
        DataResultList<DonationData> donationDataResultList = new DataResultList<>();
        donationDataResultList.setResponseStatus(ResponseStatus.SUCCESS);
        donationDataResultList.setStatus(ResponseStatus.SUCCESS.getStatusCode());

        try{
            List<Donation> donations = donationRepository.findAll();

            donationDataResultList.setData(getAllDonationList(donations));

        } catch (Exception e){
            donationDataResultList.setStatus(ResponseStatus.INTERNAL_SERVER_ERROR.getStatusCode());
            donationDataResultList.setResponseStatus(ResponseStatus.INTERNAL_SERVER_ERROR);
        }
        return donationDataResultList;
    }

    private List<UserDonations> getUserDonationList(List<Donation> donations){
        List<UserDonations> donationDataList = new ArrayList<>();

        for (Donation donation: donations){
            UserDonations donationData = new UserDonations(donation.getDonatedDate(),donation.getQuantity(),donation.getCamp().getCampTitle(),
                    donation.getCamp().getState(), donation.getCamp().getState(), donation.getDetails());
            donationDataList.add(donationData);
        }
        return donationDataList;
    }

    private List<DonationData> getAllDonationList(List<Donation> donations){
        List<DonationData> donationDataList = new ArrayList<>();

        for (Donation donation: donations){
            DonationData donationData = new DonationData(donation.getId(),donation.getDonatedDate(),donation.getQuantity(),donation.getDetails(),
                    donation.getGroup().getId(), donation.getUser().getId(), donation.getCamp().getId(), donation.getCamp().getCampTitle(),
                    donation.getCamp().getState(), donation.getCamp().getCity(), donation.getUser().getFirstName(), donation.getUser().getLastName(),
                    donation.getGroup().getName(), donation.getUser().getPersonalNumber());
            donationDataList.add(donationData);
        }
        return donationDataList;
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

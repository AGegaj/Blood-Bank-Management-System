package org.fiek.bloodmanagementsystem.service;

import org.fiek.bloodmanagementsystem.common.ResponseResult;
import org.fiek.bloodmanagementsystem.entity.Camp;
import org.fiek.bloodmanagementsystem.entity.Donation;
import org.fiek.bloodmanagementsystem.entity.User;
import org.fiek.bloodmanagementsystem.model.DonationRegister;
import org.fiek.bloodmanagementsystem.repository.CampRepository;
import org.fiek.bloodmanagementsystem.repository.DonationRepository;
import org.fiek.bloodmanagementsystem.repository.UserRepository;
import org.fiek.bloodmanagementsystem.type.ResponseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class DonationService {

    @Autowired
    private CampRepository campRepository;

    @Autowired
    private DonationRepository donationRepository;

    @Autowired
    private UserRepository userRepository;

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

            donationRepository.save(donation);
        } catch (Exception e){
            responseResult.setResponseStatus(ResponseStatus.INTERNAL_SERVER_ERROR);
            responseResult.setStatus(ResponseStatus.INTERNAL_SERVER_ERROR.getStatusCode());
            responseResult.setMessage(ResponseStatus.INTERNAL_SERVER_ERROR.getMsg());
        }

        return responseResult;
    }
}

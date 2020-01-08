package org.fiek.bloodmanagementsystem.controller;

import org.fiek.bloodmanagementsystem.common.AbstractController;
import org.fiek.bloodmanagementsystem.common.DataResult;
import org.fiek.bloodmanagementsystem.common.ResponseResult;
import org.fiek.bloodmanagementsystem.model.*;
import org.fiek.bloodmanagementsystem.service.DonationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin
@RestController
@RequestMapping("donation")
public class DonationController extends AbstractController {

    @Autowired
    private DonationService donationService;

    @PostMapping(value = "/create", consumes = "application/json")
    public ResponseEntity<?> createDonation(@RequestBody DonationRegister donationRegister, HttpServletRequest request){

        ResponseResult response = donationService.createDonation(donationRegister);

        return prepareResponse(response, request);
    }

    @PostMapping(value = "/update", consumes = "application/json")
    public ResponseEntity<?> updateDonation(@RequestBody DonationUpdate donationUpdate, HttpServletRequest request){

        ResponseResult response = donationService.updateDonation(donationUpdate);

        return prepareResponse(response, request);
    }


}

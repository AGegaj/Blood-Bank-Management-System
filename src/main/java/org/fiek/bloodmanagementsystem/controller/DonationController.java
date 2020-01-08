package org.fiek.bloodmanagementsystem.controller;

import org.fiek.bloodmanagementsystem.common.AbstractController;
import org.fiek.bloodmanagementsystem.common.ResponseResult;
import org.fiek.bloodmanagementsystem.model.CampRegister;
import org.fiek.bloodmanagementsystem.model.DonationRegister;
import org.fiek.bloodmanagementsystem.service.DonationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

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
}

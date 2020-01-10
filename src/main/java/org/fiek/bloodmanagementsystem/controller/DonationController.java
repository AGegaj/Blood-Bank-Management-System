package org.fiek.bloodmanagementsystem.controller;

import org.fiek.bloodmanagementsystem.common.AbstractController;
import org.fiek.bloodmanagementsystem.common.DataResult;
import org.fiek.bloodmanagementsystem.common.DataResultList;
import org.fiek.bloodmanagementsystem.common.ResponseResult;
import org.fiek.bloodmanagementsystem.model.*;
import org.fiek.bloodmanagementsystem.service.DonationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("donation")
public class DonationController extends AbstractController {

    @Autowired
    private DonationService donationService;

    @PostMapping(value = "/create", consumes = "application/json")
    public ResponseEntity<?> createDonation(@RequestBody @Valid DonationRegister donationRegister, BindingResult bindingResult, HttpServletRequest request){

        if (bindingResult.hasErrors()){
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(getFieldError(bindingResult));
        }
        ResponseResult response = donationService.createDonation(donationRegister);

        return prepareResponse(response, request);
    }

    @PostMapping(value = "/update", consumes = "application/json")
    public ResponseEntity<?> updateDonation(@RequestBody DonationUpdate donationUpdate, HttpServletRequest request){

        ResponseResult response = donationService.updateDonation(donationUpdate);

        return prepareResponse(response, request);
    }

    @GetMapping(value = "/getAllByUserId", produces = "application/json")
    public ResponseEntity<?> getAll(@RequestParam("userId") Long userId, HttpServletRequest request){

        DataResultList<UserDonations> resultList = donationService.getAllByUser(userId);

        return prepareResponse(resultList, request);
    }

    @GetMapping(value = "/getAll", produces = "application/json")
    public ResponseEntity<?> getAll(HttpServletRequest request){

        DataResultList<DonationData> resultList = donationService.getAllDonation();

        return prepareResponse(resultList, request);
    }


}

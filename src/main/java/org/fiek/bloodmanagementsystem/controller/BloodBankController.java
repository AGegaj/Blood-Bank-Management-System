package org.fiek.bloodmanagementsystem.controller;

import org.fiek.bloodmanagementsystem.common.AbstractController;
import org.fiek.bloodmanagementsystem.common.DataResultList;
import org.fiek.bloodmanagementsystem.model.BloodBankData;
import org.fiek.bloodmanagementsystem.service.BloodBankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin
@RestController
@RequestMapping("/blood-bank")
public class BloodBankController extends AbstractController {

    @Autowired
    private BloodBankService bloodBankService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/get")
    public ResponseEntity<?> get(HttpServletRequest request){
        DataResultList<BloodBankData> dataDataResultList = bloodBankService.get();

        return prepareResponse(dataDataResultList, request);
    }
}

package org.fiek.bloodmanagementsystem.controller;

import org.fiek.bloodmanagementsystem.common.AbstractController;
import org.fiek.bloodmanagementsystem.common.DataResult;
import org.fiek.bloodmanagementsystem.common.DataResultList;
import org.fiek.bloodmanagementsystem.common.ResponseResult;
import org.fiek.bloodmanagementsystem.model.CampData;
import org.fiek.bloodmanagementsystem.model.CampRegister;
import org.fiek.bloodmanagementsystem.model.CampUpdate;
import org.fiek.bloodmanagementsystem.service.CampService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("camp")
public class CampController extends AbstractController {

    @Autowired
    private CampService campService;

    @PostMapping(value = "/create", consumes = "application/json")
    public ResponseEntity<?> createCamp(@RequestBody @Valid CampRegister campRegister, BindingResult bindingResult, HttpServletRequest request){
        if (bindingResult.hasErrors()){
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(getFieldError(bindingResult));
        }
        ResponseResult response = campService.createCmp(campRegister);

        return prepareResponse(response, request);
    }

    @PostMapping(value = "/update", consumes = "application/json")
    public ResponseEntity<?> updateCamp(@RequestBody CampUpdate campUpdate,  HttpServletRequest request){

        ResponseResult response = campService.updateCmp(campUpdate);

        return prepareResponse(response, request);
    }

    @GetMapping(value = "/get", produces = "application/json")
    public ResponseEntity<?> getById(@RequestParam("campId") Long campId, HttpServletRequest request){
        DataResult<CampData> result = campService.findCmpById(campId);

        return prepareResponse(result, request);
    }

    @GetMapping(value = "/getAll", produces = "application/json")
    public ResponseEntity<?> getAll(HttpServletRequest request){

        DataResultList<CampData> resultList = campService.getAllCmp();

        return prepareResponse(resultList, request);
    }
}

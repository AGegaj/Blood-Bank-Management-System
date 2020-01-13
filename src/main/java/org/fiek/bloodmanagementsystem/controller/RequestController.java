package org.fiek.bloodmanagementsystem.controller;

import org.fiek.bloodmanagementsystem.common.AbstractController;
import org.fiek.bloodmanagementsystem.common.DataResultList;
import org.fiek.bloodmanagementsystem.common.ResponseResult;
import org.fiek.bloodmanagementsystem.model.BloodGroupRequest;
import org.fiek.bloodmanagementsystem.model.RequestData;
import org.fiek.bloodmanagementsystem.model.RequestRegister;
import org.fiek.bloodmanagementsystem.model.UserRequest;
import org.fiek.bloodmanagementsystem.service.RequestService;
import org.fiek.bloodmanagementsystem.type.RequestStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin
@RestController
@RequestMapping("request")
public class RequestController extends AbstractController {

    @Autowired
    private RequestService requestService;

    @PostMapping(value = "/create", consumes = "application/json")
    public ResponseEntity<?> createRequest(@RequestBody RequestRegister requestRegister, HttpServletRequest request){
        ResponseResult response = requestService.createRequest(requestRegister);

        return prepareResponse(response, request);
    }

    @GetMapping(value = "/getAll", produces = "application/json")
    public ResponseEntity<?> getAll(HttpServletRequest request){

        DataResultList<RequestData> resultList = requestService.getAllRequest();

        return prepareResponse(resultList, request);
    }

    @GetMapping(value = "/filter-request", produces = "application/json")
    public ResponseEntity<?> filterByStatus(@RequestParam("status") String status, HttpServletRequest request){

        DataResultList<RequestData> resultList = requestService.filterByStatus(status);

        return prepareResponse(resultList, request);
    }

    @GetMapping(value = "/getAllByUserId", produces = "application/json")
    public ResponseEntity<?> getAllByUserId(@RequestParam("userId") Long userId, HttpServletRequest request){

        DataResultList<UserRequest> resultList = requestService.getAllRequestByUser(userId);

        return prepareResponse(resultList, request);
    }

    @GetMapping(value = "/getAllByBloodGroup", produces = "application/json")
    public ResponseEntity<?> getAllByBloodGroup(@RequestParam("groupId") Long groupId, HttpServletRequest request){

        DataResultList<BloodGroupRequest> resultList = requestService.getAllRequestByBloodGroup(groupId);

        return prepareResponse(resultList, request);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(value = "/confirm")
    public ResponseEntity<?> confirm(@RequestParam("requestId") Long requestId, HttpServletRequest request){
        ResponseResult responseResult = requestService.confirmRequest(requestId);

        return prepareResponse(responseResult, request);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(value = "/delete")
    public ResponseEntity<?> delete(@RequestParam("requestId") Long requestId, HttpServletRequest request){
        ResponseResult responseResult = requestService.deleteRequest(requestId);

        return prepareResponse(responseResult, request);
    }

}

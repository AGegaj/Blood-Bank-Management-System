package org.fiek.bloodmanagementsystem.controller;

import org.fiek.bloodmanagementsystem.common.AbstractController;
import org.fiek.bloodmanagementsystem.common.ResponseResult;
import org.fiek.bloodmanagementsystem.common.DataResult;
import org.fiek.bloodmanagementsystem.common.DataResultList;
import org.fiek.bloodmanagementsystem.model.*;
import org.fiek.bloodmanagementsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("bloodmanagement/user")
public class UserController extends AbstractController {

    @Autowired
    private UserService userService;


    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping(value = "/create", consumes = "application/json")
    public ResponseEntity<?> create(@RequestBody @Valid UserRegister userRegister, BindingResult bindingResult, HttpServletRequest request){

        if (bindingResult.hasErrors()){
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(getFieldError(bindingResult));
        }
        ResponseResult response =  userService.create(userRegister);

        return prepareResponse(response, request);
    }


    @PostMapping(value = "/update", consumes = "application/json")
    public ResponseEntity<?> update(@RequestBody UserUpdate userUpdate, HttpServletRequest request){

        ResponseResult response =  userService.update(userUpdate);

        return prepareResponse(response, request);
    }

    @PostMapping(value = "/update-password", consumes = "application/json")
    public ResponseEntity<?> updatePassword(@RequestBody @Valid  PasswordUpdate passwordUpdate, BindingResult bindingResult, HttpServletRequest request){

        if (bindingResult.hasErrors()){
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(getFieldError(bindingResult));
        }
        ResponseResult response =  userService.updatePassword(passwordUpdate);

        return prepareResponse(response, request);
    }


    @GetMapping(value = "/get", produces = "application/json")
    public ResponseEntity<?> getById(@RequestParam("userId") Long userId, HttpServletRequest request){
        DataResult<UserData> result = userService.getById(userId);

        return prepareResponse(result, request);
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(value = "/getAll", produces = "application/json")
    public ResponseEntity<?> getAll(HttpServletRequest request){

        DataResultList<DonatorData> resultList = userService.getAll();

        return prepareResponse(resultList, request);
    }

    @GetMapping(value = "/get-donator", produces = "application/json")
    public ResponseEntity<?> getDonatorById(@RequestParam("id") Long id, HttpServletRequest request){

        DataResult<DonatorData> data = userService.getDonatorById(id);

        return prepareResponse(data, request);
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(value = "/delete")
    public ResponseEntity<?> delete(@RequestParam("id") Long id, HttpServletRequest request){

        ResponseResult response = userService.delete(id);

        return prepareResponse(response, request);
    }


    @GetMapping(value = "/role")
    public ResponseEntity<?> getAllRole(HttpServletRequest request){
        DataResultList<RoleData> resultList = userService.getRoles();

        return prepareResponse(resultList, request);
    }

    @GetMapping(value = "/groups")
    public ResponseEntity<?> getAllBloodGroup(HttpServletRequest request){
        DataResultList<BloodGroupData> dataResult = userService.getBloodGroups();

        return prepareResponse(dataResult, request);
    }

    @PostMapping(value = "/filter", produces = "application/json")
    public ResponseEntity<?> getAll(@RequestBody FilterUserParameters filterUserParameters, HttpServletRequest request){

        DataResultList<DonatorData> resultList = userService.filter(filterUserParameters);

        return prepareResponse(resultList, request);
    }

}

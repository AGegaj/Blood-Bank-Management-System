package org.fiek.bloodmanagementsystem.controller;

import org.fiek.bloodmanagementsystem.common.AbstractController;
import org.fiek.bloodmanagementsystem.common.ResponseResult;
import org.fiek.bloodmanagementsystem.common.DataResult;
import org.fiek.bloodmanagementsystem.common.DataResultList;
import org.fiek.bloodmanagementsystem.model.UserData;
import org.fiek.bloodmanagementsystem.model.UserRegister;
import org.fiek.bloodmanagementsystem.model.UserUpdate;
import org.fiek.bloodmanagementsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("bloodmanagement/user")
public class UserController extends AbstractController {

    @Autowired
    private UserService userService;


    @PostMapping(value = "/create", consumes = "application/json")
    public ResponseEntity<?> create(@RequestBody UserRegister userRegister, HttpServletRequest request){

        ResponseResult response =  userService.create(userRegister);

        return prepareResponse(response, request);
    }


    @PostMapping(value = "/update", consumes = "application/json")
    public ResponseEntity<?> update(@RequestBody UserUpdate userUpdate, HttpServletRequest request){

        ResponseResult response =  userService.update(userUpdate);

        return prepareResponse(response, request);
    }


    //TODO Authorize only for administrator
    @GetMapping(value = "/get", produces = "application/json")
    public ResponseEntity<?> getById(@RequestParam("userId") Long userId, HttpServletRequest request){
        DataResult<UserData> result = userService.getById(userId);

        return prepareResponse(result, request);
    }


    @GetMapping(value = "/getAll", produces = "application/json")
    public ResponseEntity<?> getAll(HttpServletRequest request){

        DataResultList<UserData> resultList = userService.getAll();

        return prepareResponse(resultList, request);
    }


    @PostMapping(value = "/delete")
    public ResponseEntity<?> delete(@RequestParam("id") Long id, HttpServletRequest request){

        ResponseResult response = userService.delete(id);

        return prepareResponse(response, request);
    }

}

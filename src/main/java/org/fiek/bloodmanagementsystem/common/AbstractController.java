package org.fiek.bloodmanagementsystem.common;

import org.fiek.bloodmanagementsystem.type.ResponseStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public abstract class AbstractController {
    protected ResponseEntity<?> prepareResponse(ResponseResult result, HttpServletRequest request) {

        ResponseEntity responseEntity;

        if (result.getResponseStatus() == ResponseStatus.OK) {
            responseEntity = ResponseEntity.status(HttpStatus.OK).body(result);
        } else if (result.getResponseStatus() == ResponseStatus.INTERNAL_SERVER_ERROR) {
            responseEntity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ErrorResponse(new Date(), result.getStatus(),"internal Server Error", result.getMessage(), request.getRequestURI()));
        } else if (result.getResponseStatus() == ResponseStatus.NOT_FOUND) {
            responseEntity = ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ErrorResponse(new Date(), result.getStatus(), "Not Found", result.getMessage(), request.getRequestURI()));
        } else if (result.getResponseStatus() == ResponseStatus.BAD_REQUEST) {
            responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ErrorResponse(new Date(), result.getStatus(), "Bad Request", result.getMessage(), request.getRequestURI()));
        } else {
            responseEntity = ResponseEntity.status(HttpStatus.CREATED).body(result);
        }
        return responseEntity;
    }

    protected ResponseEntity<?> prepareResponse(DataResult dataResult, HttpServletRequest request){

        ResponseEntity responseEntity;

        if (dataResult.getResponseStatus() == ResponseStatus.INTERNAL_SERVER_ERROR) {
            responseEntity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ErrorResponse(new Date(), dataResult.getStatus(), ResponseStatus.INTERNAL_SERVER_ERROR.getMsg(), "Server Error", request.getRequestURI()));
        } else if (dataResult.getResponseStatus() == ResponseStatus.NOT_FOUND) {
            responseEntity = ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ErrorResponse(new Date(), dataResult.getStatus(), ResponseStatus.NOT_FOUND.getMsg(), ResponseStatus.NO_DATA.getMsg(), request.getRequestURI()));
        } else {
            responseEntity = ResponseEntity.status(HttpStatus.OK).body(dataResult);
        }

        return responseEntity;
    }

    protected ResponseEntity<?> prepareResponse(DataResultList dataResult, HttpServletRequest request){

        ResponseEntity responseEntity;

        if (dataResult.getResponseStatus() == ResponseStatus.INTERNAL_SERVER_ERROR) {
            responseEntity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ErrorResponse(new Date(), dataResult.getStatus(), ResponseStatus.INTERNAL_SERVER_ERROR.getMsg(), "Server Error", request.getRequestURI()));
        } else if (dataResult.getResponseStatus() == ResponseStatus.NO_DATA) {
            responseEntity = ResponseEntity.status(HttpStatus.NO_CONTENT).body(
                    new ErrorResponse(new Date(), dataResult.getStatus(), ResponseStatus.NO_DATA.getMsg(), "No Content", request.getRequestURI()));
        } else {
            responseEntity = ResponseEntity.status(HttpStatus.OK).body(dataResult);
        }

        return responseEntity;
    }


    protected FieldErrorResultObject getFieldError(BindingResult bindingResult) {

        FieldErrorResultObject resultObject = new FieldErrorResultObject();
        resultObject.setStatus(ResponseStatus.FIELD_ERROR.getStatusCode());
        resultObject.setResponseStatus(ResponseStatus.FIELD_ERROR);

        List<FieldError> fieldsError = new ArrayList<>();

        for (org.springframework.validation.FieldError fe : bindingResult.getFieldErrors()) {
            FieldError fieldError = new FieldError();
            fieldError.setField(fe.getField());
            fieldError.setMessage(fe.getDefaultMessage());

            fieldsError.add(fieldError);
        }

        resultObject.setFieldsError(fieldsError);
        return resultObject;
    }
}

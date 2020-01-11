package org.fiek.bloodmanagementsystem.service;

import org.fiek.bloodmanagementsystem.common.AbstractService;
import org.fiek.bloodmanagementsystem.common.DataResultList;
import org.fiek.bloodmanagementsystem.common.ResponseResult;
import org.fiek.bloodmanagementsystem.entity.BloodBank;
import org.fiek.bloodmanagementsystem.entity.BloodGroup;
import org.fiek.bloodmanagementsystem.entity.Request;
import org.fiek.bloodmanagementsystem.entity.User;
import org.fiek.bloodmanagementsystem.mail.MailService;
import org.fiek.bloodmanagementsystem.mail.MailTemplate;
import org.fiek.bloodmanagementsystem.model.*;
import org.fiek.bloodmanagementsystem.repository.BloodBankRepository;
import org.fiek.bloodmanagementsystem.repository.RequestRepository;
import org.fiek.bloodmanagementsystem.repository.UserRepository;
import org.fiek.bloodmanagementsystem.type.Group;
import org.fiek.bloodmanagementsystem.type.RequestStatus;
import org.fiek.bloodmanagementsystem.type.ResponseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class RequestService {

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BloodBankRepository bloodBankRepository;

    @Autowired
    private MailService mailService;

    public ResponseResult createRequest(RequestRegister requestRegister){
        ResponseResult responseResult = new ResponseResult();

        responseResult.setMessage(ResponseStatus.SUCCESS.getMsg());
        responseResult.setStatus(ResponseStatus.SUCCESS.getStatusCode());
        responseResult.setResponseStatus(ResponseStatus.SUCCESS);

        try {
            Request request = new Request();

            request.setRequiredDate(new Date());

            Optional<User> user = userRepository.findById(requestRegister.getUserId());
            request.setUser(user.get());

            request.setGroup(user.get().getDonatorDetails().getGroup());
            request.setStatus(RequestStatus.UNCONFIRMED);

            requestRepository.save(request);

        } catch (Exception e){
            System.err.println(e.getMessage());
            responseResult.setResponseStatus(ResponseStatus.INTERNAL_SERVER_ERROR);
            responseResult.setStatus(ResponseStatus.INTERNAL_SERVER_ERROR.getStatusCode());
            responseResult.setMessage(ResponseStatus.INTERNAL_SERVER_ERROR.getMsg());
        }

        return responseResult;
    }

    public DataResultList<RequestData> getAllRequest(){
        DataResultList<RequestData> campDataResultList = new DataResultList<>();
        campDataResultList.setResponseStatus(ResponseStatus.SUCCESS);
        campDataResultList.setStatus(ResponseStatus.SUCCESS.getStatusCode());

        try{
            List<Request> requests = requestRepository.findAll();

            campDataResultList.setData(getRequestList(requests));

        } catch (Exception e){
            campDataResultList.setStatus(ResponseStatus.INTERNAL_SERVER_ERROR.getStatusCode());
            campDataResultList.setResponseStatus(ResponseStatus.INTERNAL_SERVER_ERROR);
        }
        return campDataResultList;
    }

    public DataResultList<UserRequest> getAllRequestByUser(Long userId){
        DataResultList<UserRequest> dataResultList = new DataResultList<>();
        dataResultList.setResponseStatus(ResponseStatus.SUCCESS);
        dataResultList.setStatus(ResponseStatus.SUCCESS.getStatusCode());

        try {
            List<Request> requests = requestRepository.findAllByUserId(userId);

            dataResultList.setData(getUserRequestList(requests));

        } catch (Exception e) {
            dataResultList.setStatus(ResponseStatus.INTERNAL_SERVER_ERROR.getStatusCode());
            dataResultList.setResponseStatus(ResponseStatus.INTERNAL_SERVER_ERROR);        }

        return dataResultList;
    }

    public DataResultList<BloodGroupRequest> getAllRequestByBloodGroup(Long groupId){
        DataResultList<BloodGroupRequest> dataResultList = new DataResultList<>();
        dataResultList.setResponseStatus(ResponseStatus.SUCCESS);
        dataResultList.setStatus(ResponseStatus.SUCCESS.getStatusCode());

        try {
            List<Request> requests = requestRepository.findAllByGroupId(groupId);

            dataResultList.setData(getBloodGroupRequestList(requests));

        } catch (Exception e) {
            dataResultList.setStatus(ResponseStatus.INTERNAL_SERVER_ERROR.getStatusCode());
            dataResultList.setResponseStatus(ResponseStatus.INTERNAL_SERVER_ERROR);        }

        return dataResultList;
    }

    public ResponseResult confirmRequest(Long id){
        ResponseResult responseResult = new ResponseResult();

        try {
            Optional<Request> optionalRequest = requestRepository.findById(id);
            Request request = optionalRequest.get();

            if(checkBloodinStock(request)){
                responseResult.setMessage(ResponseStatus.SUCCESS.getMsg());
                responseResult.setStatus(ResponseStatus.SUCCESS.getStatusCode());
                responseResult.setResponseStatus(ResponseStatus.SUCCESS);

                request.setStatus(RequestStatus.CONFIRMED);
                requestRepository.save(request);

                MailTemplate mailTemplate = new MailTemplate();
                mailTemplate.setFrom("bloodonation@gmail.com");
                mailTemplate.setTo(request.getUser().getEmail());
                mailTemplate.setSubject("Confirmation Blood Request");
                mailTemplate.setText("Hi " + request.getUser().getFirstName() + " " + request.getUser().getLastName() +
                        ",\n\nYou recently requested for taking blood. \n\nYour request has been confirmed, so please come to our" +
                        "Blood Bank as soon as you can. \n\nHope you get to feeling better soon!");
                mailService.sendEmail(mailTemplate);
            } else {
                responseResult.setMessage("Stock empty !");
                responseResult.setStatus(ResponseStatus.NO_DATA.getStatusCode());
                responseResult.setResponseStatus(ResponseStatus.NO_DATA);
                request.setStatus(RequestStatus.UNCONFIRMED);
                requestRepository.save(request);
            }


        } catch (Exception e){
            System.err.println(e.getMessage());
            responseResult.setStatus(ResponseStatus.INTERNAL_SERVER_ERROR.getStatusCode());
            responseResult.setMessage(ResponseStatus.INTERNAL_SERVER_ERROR.getMsg());
            responseResult.setResponseStatus(ResponseStatus.INTERNAL_SERVER_ERROR);
        }

        return responseResult;
    }

    public ResponseResult deleteRequest(Long id){
        ResponseResult responseResult = new ResponseResult();

        responseResult.setMessage(ResponseStatus.SUCCESS.getMsg());
        responseResult.setStatus(ResponseStatus.SUCCESS.getStatusCode());
        responseResult.setResponseStatus(ResponseStatus.SUCCESS);

        try {
            Optional<Request> optionalRequest = requestRepository.findById(id);
            if(!optionalRequest.isPresent()){
                responseResult.setResponseStatus(ResponseStatus.NOT_FOUND);
                responseResult.setMessage(ResponseStatus.NOT_FOUND.getMsg());
                responseResult.setStatus(ResponseStatus.NOT_FOUND.getStatusCode());

                return responseResult;
            }
            Request request = optionalRequest.get();
            requestRepository.delete(request);


        } catch (Exception e){
            System.err.println(e.getMessage());
            responseResult.setStatus(ResponseStatus.INTERNAL_SERVER_ERROR.getStatusCode());
            responseResult.setMessage(ResponseStatus.INTERNAL_SERVER_ERROR.getMsg());
            responseResult.setResponseStatus(ResponseStatus.INTERNAL_SERVER_ERROR);
        }

        return responseResult;
    }



    private List<RequestData> getRequestList(List<Request> requests){
        List<RequestData> requestDataList = new ArrayList<>();

        for (Request request: requests){
            RequestData requestData = new RequestData(request.getId(),request.getRequiredDate(),request.getGroup().getName(),
                    request.getUser().getId(),request.getUser().getFirstName(),request.getUser().getLastName(),request.getUser().getEmail(),request.getUser().getPersonalNumber(), request.getStatus());
            requestDataList.add(requestData);
        }
        return requestDataList;
    }

    private List<UserRequest> getUserRequestList(List<Request> requests){
        List<UserRequest> requestDataList = new ArrayList<>();

        for (Request request: requests){
            UserRequest requestData = new UserRequest(request.getId(), request.getRequiredDate(), request.getGroup().getName());
            requestDataList.add(requestData);
        }
        return requestDataList;
    }

    private List<BloodGroupRequest> getBloodGroupRequestList(List<Request> requests){
        List<BloodGroupRequest> requestDataList = new ArrayList<>();

        for (Request request: requests){
            BloodGroupRequest requestData = new BloodGroupRequest(request.getId(), request.getRequiredDate(), request.getUser().getId(), request.getUser().getFirstName(), request.getUser().getLastName(),
                    request.getUser().getEmail(), request.getUser().getPersonalNumber());
            requestDataList.add(requestData);
        }
        return requestDataList;
    }

    private Boolean checkBloodinStock(Request request){

        double quantity = 0;

        List<BloodBank> getAllBlood = bloodBankRepository.findAll();
        List<BloodBank> bloodBankOminus = bloodBankRepository.findByGroup(Group.OMINUS.fromCanonicalForm());
        List<BloodBank> bloodBankOplus = bloodBankRepository.findByGroup(Group.OPLUS.fromCanonicalForm());
        List<BloodBank> bloodBankAminus = bloodBankRepository.findByGroup(Group.AMINUS.fromCanonicalForm());
        List<BloodBank> bloodBankAplus = bloodBankRepository.findByGroup(Group.APLUS.fromCanonicalForm());
        List<BloodBank> bloodBankBminus = bloodBankRepository.findByGroup(Group.BMINUS.fromCanonicalForm());
        List<BloodBank> bloodBankBplus = bloodBankRepository.findByGroup(Group.BPLUS.fromCanonicalForm());
        List<BloodBank> bloodBankABminus = bloodBankRepository.findByGroup(Group.ABMINUS.fromCanonicalForm());

        switch (request.getGroup().getName()){
            case "0+":
                if (!bloodBankOminus.isEmpty()){
                   quantity = calculateQuantity(bloodBankOminus);
                } else {
                    quantity = calculateQuantity(bloodBankOplus);
                }
                break;
            case "0-":
                quantity = calculateQuantity(bloodBankOminus);
                break;
            case "A+":
                if (!bloodBankAplus.isEmpty()){ quantity = calculateQuantity(bloodBankAplus);
                } else if(!bloodBankAminus.isEmpty()) { quantity = calculateQuantity(bloodBankAminus);
                } else if(!bloodBankOplus.isEmpty()) { quantity = calculateQuantity(bloodBankOplus);
                } else { quantity = calculateQuantity(bloodBankOminus); }
                break;
            case "A-":
                if (!bloodBankAminus.isEmpty()) { quantity = calculateQuantity(bloodBankAminus);
                } else {quantity = calculateQuantity(bloodBankOminus); }
                break;
            case "B+":
                if (!bloodBankBplus.isEmpty()){ quantity = calculateQuantity(bloodBankBplus);
                } else if(!bloodBankBminus.isEmpty()) { quantity = calculateQuantity(bloodBankBminus);
                } else if(!bloodBankOplus.isEmpty()) { quantity = calculateQuantity(bloodBankOplus);
                } else { quantity = calculateQuantity(bloodBankOminus); }
                break;
            case "B-":
                if (!bloodBankBminus.isEmpty()) { quantity = calculateQuantity(bloodBankBminus);
                } else {quantity = calculateQuantity(bloodBankOminus); }
                break;
            case "AB+":
                quantity = calculateQuantity(getAllBlood);
                break;
            case "AB-":
                if (!bloodBankABminus.isEmpty()){ quantity = calculateQuantity(bloodBankABminus);
                } else if(!bloodBankAminus.isEmpty()) { quantity = calculateQuantity(bloodBankAminus);
                } else if(!bloodBankBminus.isEmpty()) { quantity = calculateQuantity(bloodBankBminus);
                } else { quantity = calculateQuantity(bloodBankOminus); }
                break;
            }
            if (quantity >= 350){
                return true;
            } else {
                return false;
            }
    }

    private double calculateQuantity(List<BloodBank> bloodBanks) {
      double quantity = 0;
        if (!bloodBanks.isEmpty()) {
            for (BloodBank bloodBank : bloodBanks) {
                quantity = quantity + bloodBank.getQuantity();
            }
        }
        return quantity;
    }
}

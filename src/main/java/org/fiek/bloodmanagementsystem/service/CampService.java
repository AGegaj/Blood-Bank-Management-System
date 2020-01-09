package org.fiek.bloodmanagementsystem.service;

import org.fiek.bloodmanagementsystem.common.AbstractService;
import org.fiek.bloodmanagementsystem.common.DataResult;
import org.fiek.bloodmanagementsystem.common.DataResultList;
import org.fiek.bloodmanagementsystem.common.ResponseResult;
import org.fiek.bloodmanagementsystem.entity.Camp;
import org.fiek.bloodmanagementsystem.entity.DonatorDetails;
import org.fiek.bloodmanagementsystem.entity.Role;
import org.fiek.bloodmanagementsystem.entity.User;
import org.fiek.bloodmanagementsystem.model.CampData;
import org.fiek.bloodmanagementsystem.model.CampRegister;
import org.fiek.bloodmanagementsystem.model.CampUpdate;
import org.fiek.bloodmanagementsystem.model.UserData;
import org.fiek.bloodmanagementsystem.repository.CampRepository;
import org.fiek.bloodmanagementsystem.type.ResponseStatus;
import org.fiek.bloodmanagementsystem.type.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CampService extends AbstractService {

    @Autowired
    private CampRepository campRepository;

    public ResponseResult createCmp(CampRegister campRegister){
        ResponseResult responseResult = new ResponseResult();

        responseResult.setMessage(ResponseStatus.SUCCESS.getMsg());
        responseResult.setStatus(ResponseStatus.SUCCESS.getStatusCode());
        responseResult.setResponseStatus(ResponseStatus.SUCCESS);

        try {
            Camp camp = campRegister.getCamp();
            if(campRegister.getImg() != null){
                camp.setImg(writeImage(campRegister.getImg(), "camp"));

            }
            campRepository.save(camp);
        } catch (Exception e){
            responseResult.setResponseStatus(ResponseStatus.INTERNAL_SERVER_ERROR);
            responseResult.setStatus(ResponseStatus.INTERNAL_SERVER_ERROR.getStatusCode());
            responseResult.setMessage(ResponseStatus.INTERNAL_SERVER_ERROR.getMsg());
        }

        return responseResult;
    }

    public ResponseResult updateCmp(CampUpdate campUpdate){
        ResponseResult result = new ResponseResult();

        result.setResponseStatus(ResponseStatus.BAD_REQUEST);

        Optional<Camp> optionalCamp = campRepository.findById(campUpdate.getCampId());

        try {
            Camp camp = optionalCamp.get();
            camp.setCampTitle(campUpdate.getCampTitle());
            camp.setState(campUpdate.getState());
            camp.setCity(campUpdate.getCity());
            camp.setDetails(campUpdate.getDetails());

            if(campUpdate.getImg() != null){
                camp.setImg(writeImage(campUpdate.getImg(), "camp"));

            }


            campRepository.save(camp);

            result.setResponseStatus(ResponseStatus.SUCCESS);
            result.setStatus(ResponseStatus.SUCCESS.getStatusCode());
            result.setMessage(ResponseStatus.SUCCESS.getMsg());

        } catch (Exception e) {
            result.setResponseStatus(ResponseStatus.INTERNAL_SERVER_ERROR);
            result.setStatus(ResponseStatus.INTERNAL_SERVER_ERROR.getStatusCode());
            result.setMessage(ResponseStatus.INTERNAL_SERVER_ERROR.getMsg());
        }
        return result;
    }

    public DataResult<CampData> findCmpById(Long campId){
        DataResult dataResult = new DataResult();
        dataResult.setResponseStatus(ResponseStatus.NOT_FOUND);
        dataResult.setStatus(ResponseStatus.NOT_FOUND.getStatusCode());

        try {
            Optional<Camp> optionalCamp = campRepository.findById(campId);
            if(!optionalCamp.isPresent())
                return dataResult;

            Camp camp = optionalCamp.get();
            CampData campData = new CampData(camp.getId(), camp.getCampTitle(), camp.getState(), camp.getCity(), camp.getDetails(), camp.getImg());
            dataResult.setResponseStatus(ResponseStatus.OK);
            dataResult.setStatus(ResponseStatus.OK.getStatusCode());
            dataResult.setData(campData);

        } catch (Exception e){
            dataResult.setStatus(ResponseStatus.INTERNAL_SERVER_ERROR.getStatusCode());
            dataResult.setResponseStatus(ResponseStatus.INTERNAL_SERVER_ERROR);
        }
        return dataResult;
    }

    public DataResultList<CampData> getAllCmp(){
        DataResultList<CampData> campDataResultList = new DataResultList<>();
        campDataResultList.setResponseStatus(ResponseStatus.SUCCESS);
        campDataResultList.setStatus(ResponseStatus.SUCCESS.getStatusCode());

        try{
            List<Camp> camps = campRepository.findAll();

            campDataResultList.setData(getCampList(camps));

        } catch (Exception e){
            campDataResultList.setStatus(ResponseStatus.INTERNAL_SERVER_ERROR.getStatusCode());
            campDataResultList.setResponseStatus(ResponseStatus.INTERNAL_SERVER_ERROR);
        }
        return campDataResultList;
    }

    private List<CampData> getCampList(List<Camp> camps){
        List<CampData> campDataList = new ArrayList<>();

        for (Camp camp: camps){
            CampData campData = new CampData(camp.getId(), camp.getCampTitle(), camp.getState(), camp.getCity(), camp.getDetails(),camp.getImg());
            campDataList.add(campData);
        }
        return campDataList;
    }

}

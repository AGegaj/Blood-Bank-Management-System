package org.fiek.bloodmanagementsystem.service;

import org.fiek.bloodmanagementsystem.common.DataResultList;
import org.fiek.bloodmanagementsystem.entity.BloodBank;
import org.fiek.bloodmanagementsystem.model.BloodBankData;
import org.fiek.bloodmanagementsystem.repository.BloodBankRepository;
import org.fiek.bloodmanagementsystem.type.ResponseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BloodBankService {

    @Autowired
    private BloodBankRepository bloodBankRepository;

    public DataResultList<BloodBankData> get(){

        DataResultList<BloodBankData> dataDataResultList = new DataResultList<>();
        dataDataResultList.setResponseStatus(ResponseStatus.SUCCESS);
        dataDataResultList.setStatus(ResponseStatus.SUCCESS.getStatusCode());

        try {
            List<BloodBank> bloodBankList = bloodBankRepository.findAll();
            List<BloodBankData> bloodBankDataList = new ArrayList<>();
            Double sum = bloodBankRepository.getSum();

            for (BloodBank bloodBank: bloodBankList){
                Double percentage = bloodBank.getQuantity() / sum * 100;
                BloodBankData bloodBankData = new BloodBankData(bloodBank.getGroup().getName(), bloodBank.getQuantity(), String.format("%.2f", percentage));
                bloodBankDataList.add(bloodBankData);
            }

            dataDataResultList.setData(bloodBankDataList);

        } catch (Exception e){
            dataDataResultList.setResponseStatus(ResponseStatus.INTERNAL_SERVER_ERROR);
            dataDataResultList.setStatus(ResponseStatus.INTERNAL_SERVER_ERROR.getStatusCode());
        }

        return dataDataResultList;
    }
}

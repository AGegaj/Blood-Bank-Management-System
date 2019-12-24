package org.fiek.bloodmanagementsystem.service;

import org.fiek.bloodmanagementsystem.common.ResponseResult;
import org.fiek.bloodmanagementsystem.common.DataResult;
import org.fiek.bloodmanagementsystem.common.DataResultList;
import org.fiek.bloodmanagementsystem.entity.DonatorDetails;
import org.fiek.bloodmanagementsystem.entity.Role;
import org.fiek.bloodmanagementsystem.entity.User;
import org.fiek.bloodmanagementsystem.model.UserData;
import org.fiek.bloodmanagementsystem.model.UserRegister;
import org.fiek.bloodmanagementsystem.model.UserUpdate;
import org.fiek.bloodmanagementsystem.repository.RoleRepository;
import org.fiek.bloodmanagementsystem.repository.UserRepository;
import org.fiek.bloodmanagementsystem.type.ResponseStatus;
import org.fiek.bloodmanagementsystem.type.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sound.midi.Soundbank;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;


    public ResponseResult create(UserRegister userRegister) {
        ResponseResult result = new ResponseResult();
        result.setResponseStatus(ResponseStatus.BAD_REQUEST);
        result.setStatus(ResponseStatus.BAD_REQUEST.getStatusCode());

        Optional<User> optionalUser = userRepository.findByUsername(userRegister.getUsername());
        if (optionalUser.isPresent()) {
            result.setMessage("This username exists!");
            return result;
        }

        optionalUser = userRepository.findByEmail(userRegister.getEmail());
        if (optionalUser.isPresent()) {
            result.setMessage("This email exists!");
            return result;
        }

        optionalUser = userRepository.findByPersonalNumber(userRegister.getPersonalNumber());
        if (optionalUser.isPresent()) {
            result.setMessage("This personal number exists!");
            return result;
        }

        try {
            Optional<Role> optionalRole = roleRepository.findById(userRegister.getRoleId());
            Role role = optionalRole.get();

            User user = userRegister.getUser();
            user.setRole(role);
            user.setCreatedTime(new Date());
            user.setStatus(Status.ACTIVE);
            //TODO encryptPassword()
            user.setPassword(userRegister.getPassword());
            if (userRegister.getDonatorDetailsRegister() != null) {
                user.setDonatorDetails(userRegister.getDonatorDetailsRegister().getDonatorDetails());
            }

            userRepository.save(user);

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


    public ResponseResult update(UserUpdate userUpdate) {
        ResponseResult result = new ResponseResult();

        result.setResponseStatus(ResponseStatus.BAD_REQUEST);

        Optional<User> optionalUser = userRepository.findByEmail(userUpdate.getEmail());
        if (optionalUser.isPresent()) {
            result.setMessage("This email exists!");
            return result;
        }

        optionalUser = userRepository.findByUsername(userUpdate.getUsername());
        if (optionalUser.isPresent()) {
            result.setMessage("This username exists!");
            return result;
        }

        try {
            optionalUser = userRepository.findById(userUpdate.getId());
            User user = optionalUser.get();
            user.setFirstName(userUpdate.getFirstName());
            user.setLastName(userUpdate.getLastName());
            user.setUsername(userUpdate.getUsername());
            user.setEmail(userUpdate.getEmail());
            user.setPassword(userUpdate.getPassword());
            user.setImage(userUpdate.getImage());
            user.setCountry(userUpdate.getCountry());
            user.setCity(userUpdate.getCity());

            if (userUpdate.getDonatorDetailsUpdate() != null) {
                DonatorDetails donatorDetails = user.getDonatorDetails();
                donatorDetails.setWeigh(userUpdate.getDonatorDetailsUpdate().getWeigh());
                donatorDetails.setAge(userUpdate.getDonatorDetailsUpdate().getAge());
                user.setDonatorDetails(donatorDetails);
            }

            userRepository.save(user);

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


    public DataResult<UserData> getById(Long userId){
        DataResult dataResult = new DataResult();
        dataResult.setResponseStatus(ResponseStatus.NOT_FOUND);
        dataResult.setStatus(ResponseStatus.NOT_FOUND.getStatusCode());

        try {
            Optional<User> optionalUser = userRepository.findById(userId);
            if(!optionalUser.isPresent())
                return dataResult;

            User user = optionalUser.get();
            UserData userData = new UserData(user.getId(), user.getFirstName(), user.getLastName(), user.getUsername(),
                                            user.getEmail(), user.getCreatedTime(), user.getImage(), user.getCountry(),
                                            user.getCity(), user.getPersonalNumber(), user.getRole().getName());
            dataResult.setResponseStatus(ResponseStatus.OK);
            dataResult.setStatus(ResponseStatus.OK.getStatusCode());
            dataResult.setData(userData);

        } catch (Exception e){
            dataResult.setStatus(ResponseStatus.INTERNAL_SERVER_ERROR.getStatusCode());
            dataResult.setResponseStatus(ResponseStatus.INTERNAL_SERVER_ERROR);
        }
        return dataResult;
    }


    public DataResultList<UserData> getAll(){
        DataResultList<UserData> userDataResultList = new DataResultList<>();
        userDataResultList.setResponseStatus(ResponseStatus.SUCCESS);
        userDataResultList.setStatus(ResponseStatus.SUCCESS.getStatusCode());

        try{
            Optional<List<User>> users = userRepository.findAllByRole("ADMIN");
            if(!users.isPresent()){
                userDataResultList.setResponseStatus(ResponseStatus.NO_DATA);
                userDataResultList.setStatus(ResponseStatus.NO_DATA.getStatusCode());
                return userDataResultList;
            }
            userDataResultList.setData(getUserList(users.get()));

        } catch (Exception e){
            userDataResultList.setStatus(ResponseStatus.INTERNAL_SERVER_ERROR.getStatusCode());
            userDataResultList.setResponseStatus(ResponseStatus.INTERNAL_SERVER_ERROR);
        }
        return userDataResultList;
    }


    public ResponseResult delete(Long id){
        ResponseResult responseResult = new ResponseResult();
        responseResult.setResponseStatus(ResponseStatus.INTERNAL_SERVER_ERROR);
        responseResult.setStatus(ResponseStatus.INTERNAL_SERVER_ERROR.getStatusCode());

        Optional<User> optionalUser = userRepository.findById(id);
        if(!optionalUser.isPresent()){
            responseResult.setResponseStatus(ResponseStatus.NOT_FOUND);
            responseResult.setStatus(ResponseStatus.NOT_FOUND.getStatusCode());
            responseResult.setMessage("This user does not exist!");

            return responseResult;
        }
        try{
            User user = optionalUser.get();
            user.setStatus(Status.DELETED);
            userRepository.save(user);

            responseResult.setResponseStatus(ResponseStatus.OK);
            responseResult.setMessage(ResponseStatus.OK.getMsg());
            responseResult.setStatus(ResponseStatus.OK.getStatusCode());
        } catch (Exception e){
            responseResult.setMessage(ResponseStatus.INTERNAL_SERVER_ERROR.getMsg());
        }

        return responseResult;
    }


    private List<UserData> getUserList(List<User> users){
        List<UserData> userDataList = new ArrayList<>();

        for (User user: users){
            UserData userData = new UserData(user.getId(), user.getFirstName(), user.getLastName(), user.getUsername(),
                    user.getEmail(), user.getCreatedTime(), user.getImage(), user.getCountry(),
                    user.getCity(), user.getPersonalNumber(), user.getRole().getName());
            userDataList.add(userData);
        }
        return userDataList;
    }
}

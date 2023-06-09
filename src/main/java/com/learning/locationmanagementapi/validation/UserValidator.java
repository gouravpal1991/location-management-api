package com.learning.locationmanagementapi.validation;

import com.learning.locationmanagementapi.constant.ErrorType;
import com.learning.locationmanagementapi.exception.ErrorModel;
import com.learning.locationmanagementapi.model.UserModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserValidator {
    public List<ErrorModel> validateRequest(UserModel userModel){

        List<ErrorModel> errorModelList = new ArrayList<>();

        if(userModel !=null && userModel.getEmail() == null){
            ErrorModel errorModel = new ErrorModel();
            errorModel.setCode(ErrorType.NOT_EMPTY.toString());
            errorModel.setMessage("Email cannot be empty!");
            errorModelList.add(errorModel);
        }

        if(userModel !=null && userModel.getPassword() == null){
            ErrorModel errorModel = new ErrorModel();
            errorModel.setCode(ErrorType.NOT_EMPTY.toString());
            errorModel.setMessage("Password cannot be empty!");
            errorModelList.add(errorModel);
        }

        return errorModelList;
    }
}

package com.learning.locationmanagementapi.converter;

import com.learning.locationmanagementapi.entity.UserEntity;
import com.learning.locationmanagementapi.model.UserModel;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    public UserEntity convertModelToEntity(UserModel userModel) {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(userModel.getEmail());
        userEntity.setFullName(userModel.getFullName());
        userEntity.setPassword(userModel.getPassword());
        userEntity.setMobileNumber(userModel.getMobileNumber());
        return userEntity;
    }
}

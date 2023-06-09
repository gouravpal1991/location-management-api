package com.learning.locationmanagementapi.service;

import com.learning.locationmanagementapi.constant.ErrorType;
import com.learning.locationmanagementapi.converter.UserConverter;
import com.learning.locationmanagementapi.entity.UserEntity;
import com.learning.locationmanagementapi.exception.BusinessException;
import com.learning.locationmanagementapi.exception.ErrorModel;
import com.learning.locationmanagementapi.model.UserModel;
import com.learning.locationmanagementapi.repository.UserEntityRepository;
import com.learning.locationmanagementapi.validation.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserEntityRepository userEntityRepository;

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private UserValidator userValidator;

    @Override
    public boolean login(UserModel userModel) throws BusinessException {
        List<ErrorModel> errorModels = userValidator.validateRequest(userModel);
        if (!CollectionUtils.isEmpty(errorModels)) {
            throw new BusinessException(errorModels);
        }

        UserEntity user = userEntityRepository.findByEmailAndPassword(userModel.getEmail(), userModel.getPassword());
        if (user == null) {
            List<ErrorModel> errorModelList = new ArrayList<>();
            ErrorModel errorModel = new ErrorModel();
            errorModel.setCode(ErrorType.AUTH_INVALID_CREDENTIALS.toString());
            errorModel.setMessage("Incorrect email or password!");

            errorModelList.add(errorModel);
            throw new BusinessException(errorModelList);
        }
        return true;
    }

    @Override
    public Long register(UserModel userModel) throws BusinessException {
        List<ErrorModel> errorModels = userValidator.validateRequest(userModel);
        if (!CollectionUtils.isEmpty(errorModels)) {
            throw new BusinessException(errorModels);
        }

//        check if user already exist
        UserEntity userEntity = userEntityRepository.findByEmail(userModel.getEmail());
        if (null != userEntity) {
            ErrorModel errorModel = new ErrorModel();
            errorModel.setCode(ErrorType.ALREADY_EXIST.toString());
            errorModel.setMessage("User already exist with the given email id. Please try with another email.");

            errorModels = new ArrayList<>();
            errorModels.add(errorModel);
            throw new BusinessException(errorModels);
        }

        UserEntity user = userConverter.convertModelToEntity(userModel);
        UserEntity savedUser = userEntityRepository.save(user);
        return savedUser.getId();
    }
}

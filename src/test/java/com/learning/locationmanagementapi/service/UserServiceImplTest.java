package com.learning.locationmanagementapi.service;

import com.learning.locationmanagementapi.converter.UserConverter;
import com.learning.locationmanagementapi.entity.UserEntity;
import com.learning.locationmanagementapi.exception.BusinessException;
import com.learning.locationmanagementapi.exception.ErrorModel;
import com.learning.locationmanagementapi.model.UserModel;
import com.learning.locationmanagementapi.repository.UserEntityRepository;
import com.learning.locationmanagementapi.validation.UserValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userServiceImpl;
    @Mock
    private UserValidator userValidator;
    @Mock
    private UserEntityRepository entityRepository;

    @Mock
    private UserConverter userConverter;

    @Test
    void test_login_error() {

        UserModel userModel = new UserModel();
        List<ErrorModel> errorModelList = new ArrayList<>();
        ErrorModel errorModel = new ErrorModel();
        errorModel.setMessage("Invalid Email");
        errorModel.setCode("Invalid Email");
        errorModelList.add(errorModel);
        Mockito.when(userValidator.validateRequest(userModel)).thenReturn(errorModelList);

        Assertions.assertThrows(BusinessException.class, () -> {
            userServiceImpl.login(userModel);
        });
    }


    @Test
    void test_login_with_wrong_credentials() {
        UserModel userModel = new UserModel();
        userModel.setEmail("abc@gmail.com");
        userModel.setPassword("abc@123");
        List<ErrorModel> errorModelList = new ArrayList<>();

        Mockito.when(userValidator.validateRequest(userModel)).thenReturn(errorModelList);
        Mockito.when(entityRepository.findByEmailAndPassword(userModel.getEmail(), userModel.getPassword())).thenReturn(null);

        Assertions.assertThrows(BusinessException.class, () -> {
            userServiceImpl.login(userModel);
        });
    }

    @Test
    void test_login_success() throws BusinessException {
        UserModel userModel = new UserModel();
        userModel.setEmail("abc@gmail.com");
        userModel.setPassword("abc@123");
        List<ErrorModel> errorModelList = new ArrayList<>();
        UserEntity user = new UserEntity();
        user.setEmail(userModel.getEmail());
        user.setPassword(userModel.getPassword());

        Mockito.when(userValidator.validateRequest(userModel)).thenReturn(errorModelList);
        Mockito.when(entityRepository.findByEmailAndPassword(userModel.getEmail(), userModel.getPassword())).thenReturn(user);

        boolean result = userServiceImpl.login(userModel);
        Assertions.assertEquals(true, result);
    }

    @Test
    void test_register_error() {
        UserModel model = new UserModel();

        List<ErrorModel> errorModelList = new ArrayList<>();
        ErrorModel errorModel = new ErrorModel();
        errorModel.setCode("Invalid email");
        errorModel.setMessage("Invalid email");

        errorModelList.add(errorModel);

        Mockito.when(userValidator.validateRequest(model)).thenReturn(errorModelList);

        Assertions.assertThrows(BusinessException.class, () -> {
            userServiceImpl.register(model);
        });
    }

    @Test
    void test_register_user_exist_error() {
        UserModel model = new UserModel();
        model.setEmail("abc@gmail.com");
        model.setPassword("abc@123");

        List<ErrorModel> errorModelList = new ArrayList<>();

        Mockito.when(userValidator.validateRequest(model)).thenReturn(errorModelList);

        Mockito.when(entityRepository.findByEmail(model.getEmail())).thenReturn(new UserEntity());

        Assertions.assertThrows(BusinessException.class, () -> {
            userServiceImpl.register(model);
        });
    }

    @Test
    void test_register_success() throws BusinessException {
        UserModel model = new UserModel();
        model.setEmail("abc@gmail.com");
        model.setPassword("abc@123");

        List<ErrorModel> errorModelList = new ArrayList<>();

        Mockito.when(userValidator.validateRequest(model)).thenReturn(errorModelList);

        Mockito.when(entityRepository.findByEmail(model.getEmail())).thenReturn(null);

        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);
        userEntity.setEmail(model.getEmail());
        userEntity.setPassword(model.getPassword());

        Mockito.when(userConverter.convertModelToEntity(model)).thenReturn(userEntity);
        Mockito.when(entityRepository.save(userEntity)).thenReturn(userEntity);

        Long id = userServiceImpl.register(model);

        Assertions.assertEquals(1, id);
    }
}

package com.learning.locationmanagementapi.converter;

import com.learning.locationmanagementapi.entity.UserEntity;
import com.learning.locationmanagementapi.model.UserModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UserConverterTest {

    @InjectMocks
    private UserConverter userConverter;

    @Test
    public void test_convertModelToEntity(){
        UserModel userModel =new UserModel();
        userModel.setEmail("abc@gmail.com");
        userModel.setPassword("abc123");
        userModel.setFullName("Gourav");
        userModel.setMobileNumber("8989898989");
        UserEntity user= userConverter.convertModelToEntity(userModel);
        Assertions.assertEquals(userModel.getEmail(), user.getEmail());
        Assertions.assertEquals(userModel.getPassword(), user.getPassword());
        Assertions.assertEquals(userModel.getFullName(), user.getFullName());
        Assertions.assertEquals(userModel.getMobileNumber(), user.getMobileNumber());
    }
}

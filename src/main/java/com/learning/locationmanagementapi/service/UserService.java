package com.learning.locationmanagementapi.service;

import com.learning.locationmanagementapi.exception.BusinessException;
import com.learning.locationmanagementapi.model.UserModel;

public interface UserService {
    boolean login(UserModel userModel) throws BusinessException;

    Long register(UserModel userModel) throws BusinessException;
}

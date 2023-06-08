package com.learning.locationmanagementapi.exception;

import java.util.List;

public class BusinessException extends Exception {
    private List<ErrorModel> errorList;

    public BusinessException(List<ErrorModel> errorList) {
        this.errorList = errorList;
    }

    public List<ErrorModel> getErrorList() {
        return errorList;
    }
}

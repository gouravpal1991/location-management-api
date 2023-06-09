package com.learning.locationmanagementapi.exception;

import lombok.Data;

@Data
public class ErrorModel {
    private String code;
    private String message;
}

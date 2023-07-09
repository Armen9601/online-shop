package com.example.onlineshop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDto {

    private String environment;
    private String message;
    private int statusCode;
    private String logLevel;
    private String functionApp;
    private String method;
    private String file;


}

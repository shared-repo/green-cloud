package com.example.ajaxdemo.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class MyData {

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime birthDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthDate2;
}

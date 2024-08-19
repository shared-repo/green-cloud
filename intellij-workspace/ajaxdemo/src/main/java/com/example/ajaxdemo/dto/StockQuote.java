package com.example.ajaxdemo.dto;

import lombok.Data;

@Data
public class StockQuote {
    private String stockCode;
    private String date;
    private double close;
    private double increase;
    private double open;
    private double high;
    private double low;
    private double volume;
}

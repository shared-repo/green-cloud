package com.example.ajaxdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Map;

//@Controller
@RestController // 모든 메서드에 @ResponseBody 자동 설정
@RequestMapping(path = { "/demo" })
public class DemoController {

    @RequestMapping(path = { "/key-economy-indicator" }, produces = "application/json;charset=utf-8")
    // @ResponseBody
    public String loadKeyEconomyIndicator() {

        String apiKey = "XIXDXQPRTA4PLPFTS37V";
        String respType = "json";
        String lang = "kr";
        int from = 1, to = 100;
        String code1 = "731Y001", code2 = "0000001";
        String cycle = "D";
        String fromDate = "20240813", toDate = "20240814";

        String apiUrl = String.format("https://ecos.bok.or.kr/api/StatisticSearch/%s/%s/%s/%d/%d/%s/%s/%s/%s/%s",
                                      apiKey, respType, lang, from, to, code1, cycle, fromDate,toDate, code2);
        String data = get(apiUrl, "GET", null);
        System.out.println(data);
        return data;
    }

    ///////////////////////////////////////////////////////


    private String get(String apiUrl, String requestMethod, Map<String, String> requestHeaders){

        HttpURLConnection con = connect(apiUrl);
        try {
            con.setRequestMethod(requestMethod);
            if (requestHeaders != null) {
                for (Map.Entry<String, String> header : requestHeaders.entrySet()) {
                    con.setRequestProperty(header.getKey(), header.getValue());
                }
            }

            int responseCode = con.getResponseCode(); // 요청 보내기 + 응답 수신
            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
                return readBody(con.getInputStream());
            } else { // 오류 발생
                return readBody(con.getErrorStream());
            }
        } catch (IOException e) {
            throw new RuntimeException("Fail to request and response", e);
        } finally {
            con.disconnect();
        }
    }

    private HttpURLConnection connect(String apiUrl){
        try {
            // 웹 요청 수행하는 객체 생성
            URL url = new URL(apiUrl);
            return (HttpURLConnection) url.openConnection();
        } catch (MalformedURLException e) {
            throw new RuntimeException("Invalid API URL format. : " + apiUrl, e);
        } catch (IOException e) {
            throw new RuntimeException("Fail to connect. : " + apiUrl, e);
        }
    }

    private String readBody(InputStream body){
        InputStreamReader streamReader = new InputStreamReader(body, StandardCharsets.UTF_8);

        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
            StringBuilder responseBody = new StringBuilder(1024);

            String line;
            while ((line = lineReader.readLine()) != null) {
                responseBody.append(line);
            }

            return responseBody.toString();
        } catch (IOException e) {
            throw new RuntimeException("Fail to read API response.", e);
        }
    }


}

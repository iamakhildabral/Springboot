package com.isitup.isitup.controller;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class URLcheckController {
    private final String SITE_IS_UP = "Site is up !";
    private final String SITE_IS_DOWN = "Site is Down !";
    private final String INCORRECT_URL = "Site is incorrect!";
    
    
    @GetMapping("/check")
    public String getURLStatusMessage(@RequestParam String url){
       String StatusMessage = "";
       try {
        URL urlObjUrl = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) urlObjUrl.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();
        int responseCodeCategory = conn.getResponseCode()/100;
        if(responseCodeCategory != 2 && responseCodeCategory != 3){
            StatusMessage = SITE_IS_DOWN;
        }else{
            StatusMessage = SITE_IS_UP;
        }
    } catch (IOException e) {
        
        StatusMessage = INCORRECT_URL;
    }

        return StatusMessage;
    }
}

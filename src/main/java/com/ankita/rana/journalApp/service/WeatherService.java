package com.ankita.rana.journalApp.service;

import com.ankita.rana.journalApp.api.response.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

// weather api email - developer_upcoming@slmail.me  token - e603242c27129dbcc929447341578c6a
//https://api.openweathermap.org/data/2.5/weather?lat=44.34&lon=10.99&appid=e603242c27129dbcc929447341578c6a
//@Component
@Service
public class WeatherService {
    @Autowired
    RestTemplate restTemplate;
   //We will get this apikey from application.properties using @Value Annotation
   // private final static String apiKey = "e603242c27129dbcc929447341578c6a";
    @Value("${weather.api.key}")
    private String apiKey;

    private final static String API = "https://api.openweathermap.org/data/2.5/weather?lat=44.34&lon=10.99&appid=API_KEY";

    public WeatherResponse getWeather() {
        String finalAPI = API.replace("API_KEY", apiKey);
        ResponseEntity<WeatherResponse> response =restTemplate.exchange(finalAPI, HttpMethod.GET,null,WeatherResponse.class);
        WeatherResponse body = response.getBody();
        return body;
    }
}

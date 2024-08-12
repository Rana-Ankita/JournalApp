package com.ankita.rana.journalApp.service;

import com.ankita.rana.journalApp.Constants.Placeholders;
import com.ankita.rana.journalApp.api.response.WeatherResponse;
import com.ankita.rana.journalApp.cache.AppCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

// weather api email - developer_upcoming@slmail.me  token - e603242c27129dbcc929447341578c6a
//https://api.openweathermap.org/data/2.5/weather?lat=44.34&lon=10.99&appid=e603242c27129dbcc929447341578c6a
//@Component
@Service
public class WeatherService {
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    AppCache appCache;

    @Autowired
    RedisService redisService;
   //We will get this apikey from application.properties using @Value Annotation
   // private final static String apiKey = "e603242c27129dbcc929447341578c6a";
    @Value("${weather.api.key}")
    private String apiKey;

    //now we will get the API data from database
   // private final static String API = "https://api.openweathermap.org/data/2.5/weather?lat=44.34&lon=10.99&appid=API_KEY";

    public WeatherResponse getWeather() {
       WeatherResponse weatherResponse =  redisService.get("weather_of", WeatherResponse.class);
        Optional<WeatherResponse> checkNull = Optional.ofNullable(weatherResponse);
        if(checkNull.isPresent()){
            return weatherResponse;
        }
        else {
            String finalAPI = appCache.appCache.get(AppCache.keys.WEATHER_API.toString()).replace(Placeholders.API_KEY, apiKey);
            ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalAPI, HttpMethod.GET, null, WeatherResponse.class);
            WeatherResponse body = response.getBody();
            if(body != null){
                redisService.set("weather_of", body , 300l);
            }
            return body;
        }
    }
}

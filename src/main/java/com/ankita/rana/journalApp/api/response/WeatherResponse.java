package com.ankita.rana.journalApp.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class WeatherResponse{
   // private Coord coord;
   //private ArrayList<Weather> weather;
   // private String base;
   private Wind wind;
    /*private Main main;
  private int visibility;
   private Wind wind;
   private Rain rain;
   private Clouds clouds;
   private int dt;
   private Sys sys;
   private int timezone;
   private int id;
   private String name;
   private int cod;


   private class Clouds{
       private int all;
   }

   private class Coord{
       private double lon;
       private double lat;
   }*/
    @Getter
    @Setter
    public class Wind{
        private double speed;
        private int deg;
        private double gust;
    }

   /* private class Main{
        private double temp;
        @JsonProperty("feels_like")
        private double feelsLike;
        @JsonProperty("temp_min")
        private double tempMin;
        @JsonProperty("temp_max")
        private double tempMax;
        private int pressure;
        private int humidity;
        @JsonProperty("sea_level")
        private int seaLevel;
        @JsonProperty("grnd_level")
        private int grndLevel;
    }*/

   /* private class Rain{
        @JsonProperty("1h")
        private double _1h;
    }



    private class Sys{
        private int type;
        private int id;
        private String country;
        private int sunrise;
        private int sunset;
    }

    private class Weather{
        private int id;
        private String main;
        private String description;
        private String icon;
    }

    private class Wind{
        private double speed;
        private int deg;
        private double gust;
    }*/
}






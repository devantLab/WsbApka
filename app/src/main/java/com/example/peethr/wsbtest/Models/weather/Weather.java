package com.example.peethr.wsbtest.Models.weather;


/**
 * Created by thomas on 05.03.18.
 */

public class Weather implements IWeather {

    private String location;
    private String temperature;
    private String description;
    private String icon;

    public Weather(String location, String temperature, String description, String icon){
        this.location = location;
        this.temperature = temperature;
        this.description = description;
        this.icon = icon;
    }
    @Override
    public String getLocation() {

        return location;
    }

    @Override
    public String getTemperature() {

        return temperature;
    }

    @Override
    public String getDescription() {

        return description;
    }

    @Override
    public String getIcon() {

        return icon;
    }
}

package com.example.peethr.wsbtest.Models.weather;

/**
 * Created by thomas on 05.03.18.
 */

public interface IWeather {

    String getLocation();
    String getTemperature();
    String getDescription();
    String getIcon();

}

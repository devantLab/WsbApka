package com.example.peethr.wsbtest.Models.weather;



public class CurrentWeather {

    private String temperature;
    private String summary;
    private String precipChance;

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getPrecipChance() {
        return precipChance;
    }

    public void setPrecipChance(String precipChance) {
        this.precipChance = precipChance;
    }
}

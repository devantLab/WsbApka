package com.example.peethr.wsbtest.models.data.weather;

public class Globals{
    private static Globals instance;

    // Global variable
    private int temperature;
    private String summary;
    private boolean ifWeatherUpdated = false;

    public static void setInstance(Globals instance) {
        Globals.instance = instance;
    }

    public boolean getIfWeatherUpdated() {
        return ifWeatherUpdated;
    }

    public void setIfWeatherUpdated(boolean ifWeatherUpdated) {
        this.ifWeatherUpdated = ifWeatherUpdated;
    }

    // Restrict the constructor from being instantiated
    private Globals(){}

    public void setTemperature(int temperature){
        this.temperature = temperature;
    }
    public int getTemperature(){
        return this.temperature;
    }

    public void setSummary(String summary){
        this.summary = summary;
    }
    public String getSummary(){
        return this.summary;
    }

    public static synchronized Globals getInstance(){
        if(instance == null){
            instance = new Globals();
        }
        return instance;
    }
}
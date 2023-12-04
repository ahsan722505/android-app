package com.example.recyclerview;
import com.google.gson.annotations.SerializedName;

public class WeatherDetails {
    @SerializedName("temp")
    private double temperature;

    public double getTemperature() {
        return temperature;
    }
}


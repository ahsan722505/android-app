package com.example.recyclerview;
import com.google.gson.annotations.SerializedName;

public class WeatherResponse {
    @SerializedName("main")
    private WeatherDetails weatherDetails;

    public WeatherDetails getWeatherDetails() {
        return weatherDetails;
    }
}


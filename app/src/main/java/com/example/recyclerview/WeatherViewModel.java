package com.example.recyclerview;

// WeatherViewModel.java
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherViewModel extends ViewModel {

    private final MutableLiveData<Double> temperatureLiveData = new MutableLiveData<>();

    public MutableLiveData<Double> getTemperatureLiveData() {
        return temperatureLiveData;
    }

    public void fetchWeather(String city, String apiKey) {
        // Retrofit setup
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/data/2.5/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        WeatherApi weatherApi = retrofit.create(WeatherApi.class);

        // Retrofit call
        Call<WeatherResponse> call = weatherApi.getWeather(city, apiKey);
        call.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                if (response.isSuccessful()) {
                    WeatherResponse weatherResponse = response.body();
                    if (weatherResponse != null) {
                        WeatherDetails weatherDetails = weatherResponse.getWeatherDetails();
                        double temperature = weatherDetails.getTemperature();
                        double temperatureInCelsius = Math.round(temperature - 273.15);
                        temperatureLiveData.setValue(temperatureInCelsius);
                    }
                }else {
                    temperatureLiveData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                // Handle failure
                temperatureLiveData.setValue(null);
            }
        });
    }

}


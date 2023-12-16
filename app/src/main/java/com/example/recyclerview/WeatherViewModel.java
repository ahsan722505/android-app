package com.example.recyclerview;

// WeatherViewModel.java
import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherViewModel extends AndroidViewModel {

    private final MutableLiveData<Double> temperatureLiveData = new MutableLiveData<>();

    public WeatherViewModel(@NonNull Application application) {
        super(application);
    }





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
                        showTemperatureNotification(temperatureInCelsius, city);
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
    private void showTemperatureNotification(double temperature, String city) {
        Context context = getApplication().getApplicationContext();

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        // Create a notification channel for Android Oreo and above
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String channelId = "weather_channel";
            CharSequence channelName = "Weather Channel";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(channelId, channelName, importance);
            notificationManager.createNotificationChannel(channel);
        }

        // Build the notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "weather_channel")
                .setSmallIcon(R.drawable.splash_image)
                .setContentTitle("Weather Update")
                .setContentText(city + "'s " + "Temperature: " + temperature + " °C")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        // Show the notification
        notificationManager.notify(1, builder.build());
    }

}


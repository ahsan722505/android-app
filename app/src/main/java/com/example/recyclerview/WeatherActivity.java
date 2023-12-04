package com.example.recyclerview;


import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherActivity extends AppCompatActivity {

    private TextView temperatureTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        temperatureTextView = findViewById(R.id.temperatureTextView);

        // Retrofit setup
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/data/2.5/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        WeatherApi weatherApi = retrofit.create(WeatherApi.class);

        // Replace "YOUR_API_KEY" with your OpenWeatherMap API key
        String apiKey = "05cf250deacdb40693ae4620094113d0";
        String city = "London"; // Replace with the desired city

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

                        // Update UI
                        temperatureTextView.setText("Temperature: " + temperature + " °C");
                    }
                }
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                // Handle failure
            }
        });
    }
}



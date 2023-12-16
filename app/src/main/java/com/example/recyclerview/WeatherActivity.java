package com.example.recyclerview;


// WeatherActivity.java
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import android.os.Bundle;
import android.view.View;
import com.example.recyclerview.databinding.ActivityWeatherBinding;

public class WeatherActivity extends AppCompatActivity {

    private WeatherViewModel weatherViewModel;
    private ActivityWeatherBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWeatherBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        weatherViewModel = new ViewModelProvider(this).get(WeatherViewModel.class);

        binding.fetchWeatherButton.setOnClickListener(v -> {
            String apiKey = "05cf250deacdb40693ae4620094113d0";
            String city = binding.cityEditText.getText().toString();
            weatherViewModel.fetchWeather(city, apiKey);
        });

        weatherViewModel.getTemperatureLiveData().observe(this, temperature -> {
            // Update UI when LiveData changes
            if(temperature == null)
                binding.temperatureTextView.setText("Make sure to enter a valid city name");
            else
                binding.temperatureTextView.setText("Temperature: " + temperature + " °C");
        });
    }
}




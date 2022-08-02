package com.nick.weatherapp.config;

import com.nick.weatherapp.CurrentWeather;
import com.nick.weatherapp.repo.WeatherRepository;
import com.nick.weatherapp.service.LiveWeatherService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class WeatherDBConfig {

    private final LiveWeatherService liveWeatherService;

    public WeatherDBConfig(LiveWeatherService liveWeatherService) {
        this.liveWeatherService = liveWeatherService;
    }


    // Weather for most common cities will populate DB at start (for quick access)
    @Bean
    CommandLineRunner commandLineRunner(WeatherRepository weatherRepository) {

        Map<String, String> cities = new HashMap<>();
        cities.put("London", "uk");
        cities.put("Paris", "fr");
        cities.put("Detroit", "us");

        return args -> {
            cities.forEach((city, code) ->
                    weatherRepository.save(liveWeatherService.getCurrentWeather(city, code))
            );
        };
    }
}
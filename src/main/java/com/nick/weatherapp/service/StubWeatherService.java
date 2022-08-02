package com.nick.weatherapp.service;

import com.nick.weatherapp.entity.CurrentWeather;
import com.nick.weatherapp.service.FormatConverters.TimeConverter;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class StubWeatherService {

    public CurrentWeather getCurrentWeather(String city, String country) {
        return new CurrentWeather(
                1L, "Detroit", "us", -14400L,
                "Clear", "clear sky", "http://openweathermap.org/img/wn/01n@4x.png",
                BigDecimal.ONE, BigDecimal.ONE, BigDecimal.ONE, BigDecimal.ONE,
                1000, 100, 10000L,
                BigDecimal.valueOf(0.5), 200,
                TimeConverter.toTimeFormat(1659349484L), TimeConverter.toTimeFormat(1659401529L)
        );
    }
}
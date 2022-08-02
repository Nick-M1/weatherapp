package com.nick.weatherapp.service;

import com.nick.weatherapp.CurrentWeather;
import com.nick.weatherapp.service.FormatConverters.TimeConverter;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.TimeZone;

@Service
public class StubWeatherService {



    public CurrentWeather getCurrentWeather(String city, String country) {
        return new CurrentWeather(
                1L, "Detroit", "us", -14400L,
                "Clear", "clear sky", "01n",
                BigDecimal.ONE, BigDecimal.ONE, BigDecimal.ONE, BigDecimal.ONE,
                1000, 100, 10000L,
                BigDecimal.valueOf(0.5), 200,
                TimeConverter.toTimeFormat(1659349484L), TimeConverter.toTimeFormat(1659401529L)
        );
    }
}
package com.nick.weatherapp.controller;

import com.nick.weatherapp.repo.WeatherRepository;
import com.nick.weatherapp.service.LiveWeatherService;
import com.nick.weatherapp.service.StubWeatherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.TimeZone;


@Controller
@RequestMapping(path="api/v1/weather")
public class WeatherController {

    private final StubWeatherService stubWeatherService;        //??
    private final LiveWeatherService liveWeatherService;        //??

    public WeatherController(StubWeatherService stubWeatherService, LiveWeatherService liveWeatherService, WeatherRepository weatherRepository) {
        this.stubWeatherService = stubWeatherService;
        this.liveWeatherService = liveWeatherService;
    }

    @GetMapping("/current/{city},{country}")
    public String getCurrentWeather(Model model, @PathVariable("city") String city, @PathVariable("country") String country) {
        model.addAttribute("currentWeather", liveWeatherService.getCurrentWeather(city, country));
        return "current-weather";
    }


}

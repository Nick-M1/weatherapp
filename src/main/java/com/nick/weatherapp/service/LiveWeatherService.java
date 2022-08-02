package com.nick.weatherapp.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nick.weatherapp.entity.CurrentWeather;
import com.nick.weatherapp.repo.WeatherRepository;
import com.nick.weatherapp.service.FormatConverters.MeasurementConverter;
import com.nick.weatherapp.service.FormatConverters.TimeConverter;
import com.nick.weatherapp.service.httperrors.RestTemplateResponseErrorHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;

import java.math.BigDecimal;
import java.net.URI;
import java.util.Optional;

@Service
public class LiveWeatherService {


    private static final String WEATHER_URL = "http://api.openweathermap.org/data/2.5/weather?q={city},{country}&APPID={key}&units=metric";

    @Value("${api.openweathermap.key}")         // This value is in resources. application.properties
    private String apiKey;

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final WeatherRepository weatherRepository;

    public LiveWeatherService(RestTemplateBuilder restTemplateBuilder, ObjectMapper objectMapper, WeatherRepository weatherRepository) {
        this.restTemplate = restTemplateBuilder.errorHandler(new RestTemplateResponseErrorHandler()).build();
        this.objectMapper = objectMapper;
        this.weatherRepository = weatherRepository;
    }

    public CurrentWeather getCurrentWeather(String city, String country) {
        Optional<CurrentWeather> inDBAlready = weatherRepository.findByCityName(city);
        if (inDBAlready.isPresent()) {
            return inDBAlready.get();
        }

        URI url = new UriTemplate(WEATHER_URL).expand(city, country, apiKey);
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        return convert(response);
    }

    private CurrentWeather convert(ResponseEntity<String> response) {
        try {
            JsonNode root = objectMapper.readTree(response.getBody());
            return new CurrentWeather(
                    root.path("id").asLong(),

                    root.path("name").asText(),
                    root.path("sys").path("country").asText(),
                    root.path("timezone").asLong(),

                    root.path("weather").get(0).path("main").asText(),
                    root.path("weather").get(0).path("description").asText(),
                    root.path("weather").get(0).path("icon").asText(),

                    BigDecimal.valueOf(root.path("main").path("temp").asDouble()),
                    BigDecimal.valueOf(root.path("main").path("feels_like").asDouble()),
                    BigDecimal.valueOf(root.path("main").path("temp_max").asDouble()),
                    BigDecimal.valueOf(root.path("main").path("temp_min").asDouble()),

                    root.path("main").path("pressure").asInt(),
                    root.path("main").path("humidity").asInt(),
                    MeasurementConverter.metersToKm(root.path("visibility").asLong()),

                    BigDecimal.valueOf(root.path("wind").path("speed").asDouble()),
                    root.path("wind").path("speed").asInt(),

                    TimeConverter.toTimeFormat(root.path("sys").path("sunrise").asLong()),
                    TimeConverter.toTimeFormat(root.path("sys").path("sunset").asLong())
            );

        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error parsing JSON", e);
        }
    }
}

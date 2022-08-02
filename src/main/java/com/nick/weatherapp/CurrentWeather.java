package com.nick.weatherapp;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity             // Hibernate DB
@Table(name = "weatherapp") //, uniqueConstraints = {@UniqueConstraint(name = "student_email_unique", columnNames = "email")})   // Database
public class CurrentWeather {

    @Id
    @SequenceGenerator(name = "weather_sequence", sequenceName = "weather_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "weather_sequence")
    private Long id;

    private String cityName;
    private String countryName;
    private Long timezone;                      // Shift in seconds from UTC

    private String weatherMain;                 // Main description
    private String weatherDescription;          // Longer description
    private String weatherIcon;                 // URL for this weather icon

    private BigDecimal tempNow;
    private BigDecimal feelsLike;
    private BigDecimal tempMax;
    private BigDecimal tempMin;

    private Integer pressure;
    private Integer humidity;
    private Long visibility;

    private BigDecimal windSpeed;
    private Integer windDegree;

    private String sunriseTime;
    private String sunsetTime;



    public CurrentWeather(){}

    public CurrentWeather(Long id, String cityName, String countryName, Long timezone, String weatherMain, String weatherDescription, String weatherIconCode, BigDecimal tempNow, BigDecimal feelsLike, BigDecimal tempMax, BigDecimal tempMin, Integer pressure, Integer humidity, Long visibility, BigDecimal windSpeed, Integer windDegree, String sunriseTime, String sunsetTime) {
        this.id = id;
        this.cityName = cityName;
        this.countryName = countryName;
        this.timezone = timezone;
        this.weatherMain = weatherMain;
        this.weatherDescription = weatherDescription;
        this.weatherIcon = "http://openweathermap.org/img/wn/" + weatherIconCode + "@4x.png";
        this.tempNow = tempNow;
        this.feelsLike = feelsLike;
        this.tempMax = tempMax;
        this.tempMin = tempMin;
        this.pressure = pressure;
        this.humidity = humidity;
        this.visibility = visibility;
        this.windSpeed = windSpeed;
        this.windDegree = windDegree;

        this.sunriseTime = sunriseTime;
        this.sunsetTime = sunsetTime;
    }


    public Long getId() {
        return id;
    }

    public String getCityName() {
        return cityName;
    }

    public String getCountryName() {
        return countryName;
    }

    public Long getTimezone() {
        return timezone;
    }

    public String getWeatherMain() {
        return weatherMain;
    }

    public String getWeatherDescription() {
        return weatherDescription;
    }

    public String getWeatherIcon() {
        return weatherIcon;
    }


    public BigDecimal getTempNow() {
        return tempNow;
    }

    public BigDecimal getFeelsLike() {
        return feelsLike;
    }

    public BigDecimal getTempMax() {
        return tempMax;
    }

    public BigDecimal getTempMin() {
        return tempMin;
    }

    public Integer getPressure() {
        return pressure;
    }

    public Integer getHumidity() {
        return humidity;
    }

    public Long getVisibility() {
        return visibility;
    }

    public BigDecimal getWindSpeed() {
        return windSpeed;
    }

    public Integer getWindDegree() {
        return windDegree;
    }

    public String getSunriseTime() {
        return sunriseTime;
    }

    public String getSunsetTime() {
        return sunsetTime;
    }
}

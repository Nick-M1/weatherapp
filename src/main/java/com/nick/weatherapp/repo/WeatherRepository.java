package com.nick.weatherapp.repo;

import com.nick.weatherapp.entity.CurrentWeather;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WeatherRepository extends JpaRepository<CurrentWeather, Long> {

//    @Query("SELECT s FROM weatherapp s WHERE s.cityName = ?1")
    Optional<CurrentWeather> findByCityName(String city);

    Boolean existsByCityName(String city);

}

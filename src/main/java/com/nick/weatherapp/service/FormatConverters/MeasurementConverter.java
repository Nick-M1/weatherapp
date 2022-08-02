package com.nick.weatherapp.service.FormatConverters;

import org.springframework.stereotype.Service;

@Service
public class MeasurementConverter {

    public static Long metersToKm(Long meters) {
        return meters / 1000;
    }
}

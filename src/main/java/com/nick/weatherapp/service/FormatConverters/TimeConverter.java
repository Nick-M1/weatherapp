package com.nick.weatherapp.service.FormatConverters;

import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

@Service
public class TimeConverter {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");

    public static String toTimeFormat(Long unixTimestamp) {
        Instant instant = Instant.ofEpochSecond(unixTimestamp);
        ZoneId zoneId = TimeZone.getDefault().toZoneId();
        return ZonedDateTime.ofInstant(instant, zoneId).format(formatter);
    }

}

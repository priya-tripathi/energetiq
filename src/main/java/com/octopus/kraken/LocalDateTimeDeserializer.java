package com.octopus.kraken;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * I want to support date-only strings like 2024-12-01 and automatically
 * add a default time (e.g., 00:00:00), hence a custom deserializer.
 */
public class LocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

    @Override
    public LocalDateTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
//        String value = p.getText();
//        if (value.length() == 10) {
//            // Handle date-only format
//            return LocalDateTime.parse(value + "T00:00:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME);
//        } else {
//            return LocalDateTime.parse(value, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
//        }
        return LocalDateTime.parse(p.getText(), formatter);
    }
}

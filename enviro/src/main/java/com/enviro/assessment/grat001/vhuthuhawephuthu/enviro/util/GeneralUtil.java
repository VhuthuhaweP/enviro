package com.enviro.assessment.grat001.vhuthuhawephuthu.enviro.util;

import com.enviro.assessment.grat001.vhuthuhawephuthu.enviro.exception.exceptions.FileDataFormatException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Component
public class GeneralUtil {
    public LocalDateTime parseLocalDateTime(String dateTimeString) {
        try {
            String dateTimeFormat = "yyyy-MM-dd'T'HH:mm";
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateTimeFormat);
            LocalDateTime localDate = LocalDateTime.parse(dateTimeString, formatter);

            return localDate;
        }
        catch (DateTimeParseException exception){
            throw new FileDataFormatException(String.format("DateTime format incorrect for {%s}. Expected format yyyy-MM-dd'T'HH:mm",dateTimeString));
        }
    }

    public LocalDate parseLocalDate(String dateString) {
        try {
            String dateFormat = "yyyy-MM-dd";
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);

            LocalDate localDate = LocalDate.parse(dateString, formatter);

            return localDate;
        }
        catch (DateTimeParseException exception){
            throw new FileDataFormatException(String.format("Date format incorrect for input {%s}. Expected format yyyy-MM-dd",dateString));
        }
    }

    public String getFileExtension(MultipartFile file) {
        String name = file.getOriginalFilename();
        int lastIndexOf = name.lastIndexOf(".");
        if (lastIndexOf == -1) {
            return "";
        }
        return name.substring(lastIndexOf + 1);
    }
}

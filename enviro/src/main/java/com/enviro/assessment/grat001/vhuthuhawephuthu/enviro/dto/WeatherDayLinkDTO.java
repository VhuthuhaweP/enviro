package com.enviro.assessment.grat001.vhuthuhawephuthu.enviro.dto;

import com.enviro.assessment.grat001.vhuthuhawephuthu.enviro.exception.exceptions.EntityToDTOException;
import com.enviro.assessment.grat001.vhuthuhawephuthu.enviro.model.WeatherConditions;
import com.enviro.assessment.grat001.vhuthuhawephuthu.enviro.model.WeatherDayLink;
import jakarta.persistence.Column;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeatherDayLinkDTO {

    LocalDate date;
    public static WeatherDayLinkDTO entityToDTO(WeatherDayLink weatherDayLink){
        try {
            return new WeatherDayLinkDTO(weatherDayLink.getDate());
        }
        catch (Exception exception){
            throw new EntityToDTOException("Error converting to WeatherDayLinkDTO");
        }
    }
    public static List<WeatherDayLinkDTO> entityToDTOList(List<WeatherDayLink> weatherDayLinkList){
        System.out.println(weatherDayLinkList.size());
        List<WeatherDayLinkDTO> weatherDayLinkDTOList = new ArrayList<>();

        weatherDayLinkList.forEach(weatherDayLink -> weatherDayLinkDTOList.add(entityToDTO(weatherDayLink)));

        return weatherDayLinkDTOList;
    }
}

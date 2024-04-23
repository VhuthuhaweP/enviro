package com.enviro.assessment.grat001.vhuthuhawephuthu.enviro.dto;

import com.enviro.assessment.grat001.vhuthuhawephuthu.enviro.exception.exceptions.EntityToDTOException;
import com.enviro.assessment.grat001.vhuthuhawephuthu.enviro.model.WeatherConditions;
import com.enviro.assessment.grat001.vhuthuhawephuthu.enviro.model.WeatherDayLink;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeatherConditionsDTO {
    LocalTime time;

    Double temperature;

    Double humidity;

    Double aqi;

    Double precipitation;

    public static WeatherConditionsDTO entityToDTO(WeatherConditions weatherConditions) {
        try {
            return new WeatherConditionsDTO(
                    weatherConditions.getTime(),
                    weatherConditions.getTemperature(),
                    weatherConditions.getHumidity(),
                    weatherConditions.getAqi(),
                    weatherConditions.getPrecipitation());

        } catch (Exception exception) {
            throw new EntityToDTOException("Error converting to WeatherConditionsDTO");
        }
    }

    public static List<WeatherConditionsDTO> entityToDTOList(List<WeatherConditions> weatherConditions){
        List<WeatherConditionsDTO> weatherConditionsDTOList = new ArrayList<>();

        weatherConditions.forEach(weatherConditionsDate -> weatherConditionsDTOList.add(entityToDTO(weatherConditionsDate)));

        return weatherConditionsDTOList;
    }
}

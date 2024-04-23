package com.enviro.assessment.grat001.vhuthuhawephuthu.enviro.service;

import com.enviro.assessment.grat001.vhuthuhawephuthu.enviro.dto.WeatherConditionsDTO;
import com.enviro.assessment.grat001.vhuthuhawephuthu.enviro.exception.exceptions.EntityObjectNotFoundException;
import com.enviro.assessment.grat001.vhuthuhawephuthu.enviro.model.WeatherDayLink;
import com.enviro.assessment.grat001.vhuthuhawephuthu.enviro.repository.WeatherConditionsRepository;
import com.enviro.assessment.grat001.vhuthuhawephuthu.enviro.repository.WeatherDayLinkRepository;
import com.enviro.assessment.grat001.vhuthuhawephuthu.enviro.util.GeneralUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class WeatherConditionsService {

    private final WeatherConditionsRepository weatherConditionsRepository;

    private final WeatherDayLinkService weatherDayLinkService;


    public ResponseEntity<List<WeatherConditionsDTO>> getAllWeatherConditions(){
        return ResponseEntity.ok(WeatherConditionsDTO.entityToDTOList(weatherConditionsRepository.findAll()));
    }

    public ResponseEntity<List<WeatherConditionsDTO>> getAllWeatherConditionsForDay(String date) {

        WeatherDayLink weatherDayLink = weatherDayLinkService.getLinkForDate(date);



        return ResponseEntity.ok(WeatherConditionsDTO.entityToDTOList(weatherDayLink.getWeatherConditionsList()));
    }
}

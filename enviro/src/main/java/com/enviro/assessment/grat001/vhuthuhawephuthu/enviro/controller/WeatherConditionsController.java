package com.enviro.assessment.grat001.vhuthuhawephuthu.enviro.controller;

import com.enviro.assessment.grat001.vhuthuhawephuthu.enviro.dto.WeatherConditionsDTO;
import com.enviro.assessment.grat001.vhuthuhawephuthu.enviro.service.WeatherConditionsService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/weather")
public class WeatherConditionsController {

    private final WeatherConditionsService weatherConditionsService;

    @GetMapping("/conditions")
    private ResponseEntity<List<WeatherConditionsDTO>> getAllWeatherConditions(){
        return  weatherConditionsService.getAllWeatherConditions();
    }

    @GetMapping("/conditions/{date}")
    public ResponseEntity<List<WeatherConditionsDTO>> getAllWeatherConditionsForDay(@PathVariable String date){
        return weatherConditionsService.getAllWeatherConditionsForDay(date);
    }
}

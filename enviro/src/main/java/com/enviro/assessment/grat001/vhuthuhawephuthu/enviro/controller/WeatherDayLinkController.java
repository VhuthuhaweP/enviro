package com.enviro.assessment.grat001.vhuthuhawephuthu.enviro.controller;

import com.enviro.assessment.grat001.vhuthuhawephuthu.enviro.dto.WeatherDayLinkDTO;
import com.enviro.assessment.grat001.vhuthuhawephuthu.enviro.service.WeatherDayLinkService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/weather-link")
@AllArgsConstructor
public class WeatherDayLinkController {

    private final WeatherDayLinkService weatherDayLinkService;
    @GetMapping("/")
    public ResponseEntity<List<WeatherDayLinkDTO>> getAllDayLinks(){
        return weatherDayLinkService.getAllDateLinks();
    }


    @DeleteMapping("/{date}")
    public ResponseEntity<String> deleteDayWeather(@PathVariable String date){
        return weatherDayLinkService.deleteDayWeather(date);
    }
}

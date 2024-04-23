package com.enviro.assessment.grat001.vhuthuhawephuthu.enviro.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
@AllArgsConstructor
public class WebService {
    private  final WeatherConditionsService weatherConditionsService;

    public String getWeatherConditions(String date, Model model){
        try {
            model.addAttribute("conditions",weatherConditionsService.getAllWeatherConditionsForDay(date).getBody());
            return "retrieve-detailed" ;
        }
        catch (Exception exception) {
            return "error";
        }

    }
}

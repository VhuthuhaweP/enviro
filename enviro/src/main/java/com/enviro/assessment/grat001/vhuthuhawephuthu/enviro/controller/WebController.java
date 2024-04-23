package com.enviro.assessment.grat001.vhuthuhawephuthu.enviro.controller;

import com.enviro.assessment.grat001.vhuthuhawephuthu.enviro.service.WebService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@AllArgsConstructor
public class WebController {
    private final WebService webService;

    @GetMapping("/upload")
    public String upload() {
        return "upload";
    }

    @GetMapping("/retrieve")
    public String retrieve(){
        return "retrieve";
    }

    @GetMapping("")
    public String home(){
        return "home";
    }

    @GetMapping("/retrieve/{date}")
    public String weatherConditionsDate(@PathVariable String date, Model model) {
        return webService.getWeatherConditions(date,model);
    }

    @GetMapping("/error")
    public String error() {
        return "error";
    }



}

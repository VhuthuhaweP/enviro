package com.enviro.assessment.grat001.vhuthuhawephuthu.enviro.service;
import com.enviro.assessment.grat001.vhuthuhawephuthu.enviro.dto.WeatherDayLinkDTO;
import com.enviro.assessment.grat001.vhuthuhawephuthu.enviro.exception.exceptions.EntityObjectNotFoundException;
import com.enviro.assessment.grat001.vhuthuhawephuthu.enviro.model.WeatherDayLink;
import com.enviro.assessment.grat001.vhuthuhawephuthu.enviro.repository.WeatherDayLinkRepository;
import com.enviro.assessment.grat001.vhuthuhawephuthu.enviro.util.GeneralUtil;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class WeatherDayLinkService {
    private static final Logger logger = LoggerFactory.getLogger(WeatherDayLinkService.class);

    private final WeatherDayLinkRepository weatherDayLinkRepository;

    private final GeneralUtil generalUtil;

    public ResponseEntity<List<WeatherDayLinkDTO>> getAllDateLinks(){
        List<WeatherDayLinkDTO> weatherDayLinkDTOS = WeatherDayLinkDTO.entityToDTOList(weatherDayLinkRepository.findAll());
        logger.info(String.format("Retrieved %s weather link objects",weatherDayLinkDTOS.size()));
        return ResponseEntity.ok(weatherDayLinkDTOS);
    }

    public WeatherDayLink getLinkForDate(String date){
        Optional<WeatherDayLink> weatherDayLink=weatherDayLinkRepository.findByDate(generalUtil.parseLocalDate(date));

        if(weatherDayLink.isPresent())
            return weatherDayLink.get();
        else
            throw new EntityObjectNotFoundException(String.format("Weather link object not found for date {%s}",date));
    }

    public ResponseEntity<String> deleteDayWeather(String date) {
        LocalDate parsedDate = generalUtil.parseLocalDate(date);
        Optional<WeatherDayLink> weatherDayLink =  weatherDayLinkRepository.findByDate(parsedDate);

        if(weatherDayLink.isPresent()) {
            weatherDayLinkRepository.delete(weatherDayLink.get());

            logger.info(String.format("Deleted weather link for date %s",date));

            return ResponseEntity.ok(String.format("Weather link deleted together with all linked conditions for date %s",date));
        }
        else
            throw new EntityObjectNotFoundException(String.format("No weather links for date {%s} not found",date));
    }
}

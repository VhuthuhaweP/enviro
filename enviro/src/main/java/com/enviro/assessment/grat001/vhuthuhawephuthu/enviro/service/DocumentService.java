package com.enviro.assessment.grat001.vhuthuhawephuthu.enviro.service;

import com.enviro.assessment.grat001.vhuthuhawephuthu.enviro.exception.exceptions.FileDataFormatException;
import com.enviro.assessment.grat001.vhuthuhawephuthu.enviro.exception.exceptions.FileReadException;
import com.enviro.assessment.grat001.vhuthuhawephuthu.enviro.exception.exceptions.UnsupportedFileTypeException;
import com.enviro.assessment.grat001.vhuthuhawephuthu.enviro.model.WeatherConditions;
import com.enviro.assessment.grat001.vhuthuhawephuthu.enviro.model.WeatherDayLink;
import com.enviro.assessment.grat001.vhuthuhawephuthu.enviro.model.enums.WeatherDataType;
import com.enviro.assessment.grat001.vhuthuhawephuthu.enviro.repository.WeatherConditionsRepository;
import com.enviro.assessment.grat001.vhuthuhawephuthu.enviro.repository.WeatherDayLinkRepository;
import com.enviro.assessment.grat001.vhuthuhawephuthu.enviro.util.GeneralUtil;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
@AllArgsConstructor
public class DocumentService {

    private final WeatherConditionsRepository weatherConditionsRepository;

    private final WeatherDayLinkRepository weatherDayLinkRepository;

    private final GeneralUtil generalUtil;

    private static final Logger logger = LoggerFactory.getLogger(WeatherDayLinkService.class);

    public ResponseEntity<String> readDocument(MultipartFile file) {
        if (generalUtil.getFileExtension(file).equals("txt")) {

            readContent(file);
            logger.info("file successfully read and data saved");
            return ResponseEntity.ok("Document read successfully");
        }

        logger.info("problem reading file");
        throw new UnsupportedFileTypeException("Only text files are acceptable");
    }

    private String readContent(MultipartFile file){

        List<WeatherConditions> conditions = new ArrayList<>();

        Set<WeatherDayLink> dateSet= new HashSet<>(weatherDayLinkRepository.findAll()) ;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {
            String line;
            WeatherConditions newone = new WeatherConditions();
            logger.info("reading file");
            while ((line = reader.readLine()) != null) {

                logger.info(String.format("current line %s",line));

                if(line.isEmpty()){
                    conditions.add(newone);
                    newone=new WeatherConditions();
                }
                else {
                    if(!checkValidity(line,newone.nextField(),newone,dateSet))
                        throw new FileDataFormatException("File data format incorrect");
                }
            }
        }
        catch (IOException exception){
            throw new FileReadException("An error occurred when reading the file");
        }

        addAllWeatherObjects(conditions);
        return "";
    }

    @Transactional
    private void addAllWeatherObjects(List<WeatherConditions> weatherConditionsList){
        weatherConditionsList.forEach(weatherConditions -> weatherConditionsRepository.save(weatherConditions));

        weatherConditionsRepository.findAll().forEach(weatherConditionsLink -> System.out.println(weatherConditionsLink));
    }

    private  void handleDayExists(Set<WeatherDayLink> weatherDayLinkSet, WeatherDayLink link, WeatherConditions newone){
        if(weatherDayLinkSet.add(link))
            newone.setWeatherDayLink(link);
        else {
            Optional<WeatherDayLink> matchingLink = weatherDayLinkSet.stream()
                    .filter(weatherDayLink -> weatherDayLink.equals(link))
                    .findFirst();

            newone.setWeatherDayLink(matchingLink.get());

        }
    }
    private Boolean checkValidity(String line, WeatherDataType weatherDataType, WeatherConditions newone, Set<WeatherDayLink> weatherDayLinkSet){

        boolean isValid;

        // switch to check based on type of weather data
        switch (weatherDataType) {
            case DATE:
                isValid = checkLineStartsWith(line,"Date");
                LocalDateTime localDateTime = generalUtil.parseLocalDateTime((getLineDate(line)));
                WeatherDayLink link = new WeatherDayLink(localDateTime.toLocalDate());
                handleDayExists(weatherDayLinkSet,link,newone);
                newone.setTime(localDateTime.toLocalTime());
                break;
            case TEMPERATURE:
                isValid = checkLineStartsWith(line,"Temperature");
                newone.setTemperature(Double.parseDouble(getLineDate(line)));
                break;
            case HUMIDITY:
                isValid = checkLineStartsWith(line,"Humidity");
                newone.setHumidity(Double.parseDouble(getLineDate(line)));
                break;
            case AQI:
                isValid = checkLineStartsWith(line,"Air Quality Index");
                newone.setAqi(Double.parseDouble(getLineDate(line)));
                break;
            case PRECIPITATION:
                isValid = checkLineStartsWith(line,"Precipitation");
                newone.setPrecipitation(Double.parseDouble(getLineDate(line)));
                break;
            default:
                isValid = false;
                break;
        }

        return isValid;
    }

    private boolean checkLineStartsWith(String line, String identifier){
        return line.startsWith(identifier);
    }

    private String getLineDate(String line) {
        if (line == null) {
            throw new FileDataFormatException("Input line cannot be null");
        }
        String[] parts = line.split(":", 2);
        if (parts.length < 2) {
            throw new FileDataFormatException("Input line does not contain expected delimiter ':'");
        }

        return line.split(":", 2)[1].trim();
    }


}

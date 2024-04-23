package com.enviro.assessment.grat001.vhuthuhawephuthu.enviro.repository;

import com.enviro.assessment.grat001.vhuthuhawephuthu.enviro.model.WeatherDayLink;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.util.Optional;

public interface WeatherDayLinkRepository extends JpaRepository<WeatherDayLink,Long> {
    Optional<WeatherDayLink> findByDate(LocalDate date);
}

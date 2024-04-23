package com.enviro.assessment.grat001.vhuthuhawephuthu.enviro.repository;

import com.enviro.assessment.grat001.vhuthuhawephuthu.enviro.model.WeatherConditions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherConditionsRepository extends JpaRepository<WeatherConditions,Long> {

}

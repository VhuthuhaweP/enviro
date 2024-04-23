package com.enviro.assessment.grat001.vhuthuhawephuthu.enviro.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = "weatherConditionsList")
public class WeatherDayLink {

    public WeatherDayLink(LocalDate date){
        this.date=date;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "weather_link_id")
    Long weatherId;


    @Column(name = "date")
    LocalDate date;

    @OneToMany(mappedBy = "weatherDayLink",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    List<WeatherConditions> weatherConditionsList;

    @Override
    public boolean equals(Object o) {
        if (o instanceof WeatherDayLink)
            return this.date.equals(((WeatherDayLink) o).date);
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(date);
    }
}

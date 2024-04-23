package com.enviro.assessment.grat001.vhuthuhawephuthu.enviro.model;

import com.enviro.assessment.grat001.vhuthuhawephuthu.enviro.model.enums.WeatherDataType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class WeatherConditions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "weather_id")
    Long weatherId;

    @Column(name = "date_time")
    LocalTime time;

    @Column(name = "temperature")
    Double temperature;

    @Column(name = "humidity")
    Double humidity;

    @Column(name = "air_qual_index")
    Double aqi;

    @Column(name = "precipitation")
    Double precipitation;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "day_link")
    WeatherDayLink weatherDayLink;

    public WeatherDataType nextField(){
        if(time==null)
            return WeatherDataType.DATE;
        else if(temperature==null)
            return WeatherDataType.TEMPERATURE;
        else if(humidity==null)
            return WeatherDataType.HUMIDITY;
        else if(aqi==null)
            return WeatherDataType.AQI;
        else if(precipitation==null)
            return WeatherDataType.PRECIPITATION;

        return  null;
    }
}

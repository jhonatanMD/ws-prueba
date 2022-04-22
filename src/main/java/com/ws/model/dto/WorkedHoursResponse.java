package com.ws.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WorkedHoursResponse {

    @JsonProperty("total_worked_hours")
    private BigDecimal totalWorkedHours;

    private Boolean success;



}

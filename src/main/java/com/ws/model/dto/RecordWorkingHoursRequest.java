package com.ws.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RecordWorkingHoursRequest {


    @NotNull(message = "employee_id es necesario")
    @JsonProperty("employee_id")
    private Long employeeId;

    @NotEmpty(message = "worked_hours es necesario")
    @JsonProperty("worked_hours")
    private String workedHours;

    @NotNull(message = "worked_date es necesario")
    @JsonProperty("worked_date")
    private LocalDate workedDate;

}

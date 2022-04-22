package com.ws.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

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
public class WorkedHoursRequest {

    @NotNull(message = "employee_id es necesario")
    @JsonProperty("employee_id")
    private Long employeeId;


    @NotNull(message = "start_date es necesario")
    @JsonProperty("start_date")
    private LocalDate startDate;

    @NotNull(message = "end_date es necesario")
    @JsonProperty("end_date")
    private LocalDate endDate;


}

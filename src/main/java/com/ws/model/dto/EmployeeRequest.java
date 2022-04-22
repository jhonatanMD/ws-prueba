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
public class EmployeeRequest {

    private Long id;

    @NotEmpty(message = "name es necesario")
    private String name;

    @NotEmpty(message = "last_name es necesario")
    @JsonProperty("last_name")
    private String lastName;

    @NotNull(message = "gender_id es necesario")
    @JsonProperty("gender_id")
    private Long genderId;

    @NotNull(message = "job_id es necesario")
    @JsonProperty("job_id")
    private Long jobId;

    @NotNull(message = "birthdate es necesario")
    private LocalDate birthdate;

}

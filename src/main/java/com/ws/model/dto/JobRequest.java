package com.ws.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobRequest {

    @NotNull(message = "job_id es necesario")
    @JsonProperty("job_id")
    private Long jodId;
}

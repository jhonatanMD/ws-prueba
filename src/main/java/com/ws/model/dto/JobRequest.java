package com.ws.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class JobRequest {

    @NotNull(message = "job_id es necesario")
    @JsonProperty("job_id")
    private Long jodId;
}

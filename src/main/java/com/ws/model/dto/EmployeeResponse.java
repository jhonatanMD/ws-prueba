package com.ws.model.dto;

import com.ws.model.Employees;
import com.ws.model.Genders;
import com.ws.model.Jobs;

import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeResponse {

    private List<Employees> employees;
    private boolean success;

}

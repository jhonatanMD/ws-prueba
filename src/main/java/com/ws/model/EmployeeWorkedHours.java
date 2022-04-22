package com.ws.model;

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
public class EmployeeWorkedHours {

    private Long id;

    private Employees employees;

    private BigDecimal workedHours;

    private LocalDate workedDate;
}

package com.ws.service;

import com.ws.model.EmployeeWorkedHours;
import com.ws.repository.model.EmployeeWorkedHoursEntity;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface EmployeeWorkedHourService {

    EmployeeWorkedHours save (EmployeeWorkedHoursEntity employeeWorkedHours);

    BigDecimal workedHoursByEmployee (Long employeeId, LocalDate startDate,LocalDate endDate);
}

package com.ws.service;

import com.ws.model.EmployeeWorkedHours;
import com.ws.repository.model.EmployeeWorkedHoursEntity;

public interface EmployeeWorkedHourService {

    EmployeeWorkedHours save (EmployeeWorkedHoursEntity employeeWorkedHours);
}

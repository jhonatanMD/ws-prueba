package com.ws.service;

import com.ws.model.Employees;
import com.ws.repository.model.EmployeesEntity;

import java.util.List;

public interface EmployeeService {

    Employees save(EmployeesEntity employee);


    List<Employees> findAll();
    List<Employees> findByJob(Long jobId);

    Boolean validEmployee(String name , String lastName);

    Boolean validEmployee(Long employeeId);

}

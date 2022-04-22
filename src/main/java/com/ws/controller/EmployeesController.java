package com.ws.controller;

import com.ws.business.EmployeeBusiness;
import com.ws.model.dto.AppResponse;
import com.ws.model.dto.EmployeeRequest;
import com.ws.model.dto.EmployeeResponse;
import com.ws.model.dto.JobRequest;
import com.ws.model.dto.RecordWorkingHoursRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/ws/employees")
@RequiredArgsConstructor
public class EmployeesController {

    private final EmployeeBusiness employeeBusiness;

    @PostMapping("/save")
    public AppResponse saveEmployee(@RequestBody @Valid EmployeeRequest employee){
        return employeeBusiness.saveEmployee(employee);
    }

    @PostMapping("/add-working-hours")
    public AppResponse addWorkingHours(@RequestBody @Valid RecordWorkingHoursRequest request){
        return employeeBusiness.recordWorkingHours(request);
    }


    @PostMapping("/find-by-job")
    public EmployeeResponse employeesByJobs (@RequestBody @Valid JobRequest request){
        return employeeBusiness.findByJob(request);
    }

}

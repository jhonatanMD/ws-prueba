package com.ws.business;

import com.ws.config.error.DefaultException;
import com.ws.config.error.NotFoundException;
import com.ws.mapper.EmployeeMapper;
import com.ws.mapper.EmployeeWorkedHoursMapper;
import com.ws.model.Employees;
import com.ws.model.dto.AppResponse;
import com.ws.model.dto.EmployeeRequest;
import com.ws.model.dto.EmployeeResponse;
import com.ws.model.dto.JobRequest;
import com.ws.model.dto.RecordWorkingHoursRequest;
import com.ws.service.EmployeeService;
import com.ws.service.EmployeeWorkedHourService;
import com.ws.service.GenderService;
import com.ws.service.JobService;
import com.ws.util.Utils;

import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;

import static com.ws.util.ResponseEnum.EMPLOYEE_IS_MINOR;
import static com.ws.util.ResponseEnum.EMPLOYEE_NOT_EXISTS;
import static com.ws.util.ResponseEnum.GENDERS_NOT_EXISTS;
import static com.ws.util.ResponseEnum.JOB_NOT_EXISTS;
import static com.ws.util.ResponseEnum.LATER_DATE;

@RequiredArgsConstructor
public class EmployeeBusiness {

    private final EmployeeService employeeService;
    private final GenderService genderService;
    private final JobService jobService;
    private final EmployeeWorkedHourService employeeWorkedHourService;
    private final EmployeeMapper employeeMapper;
    private final EmployeeWorkedHoursMapper workedHoursMapper;

    public AppResponse saveEmployee(EmployeeRequest employee){

         int age = LocalDate.now().getYear() - employee.getBirthdate().getYear();

         if(age < 18)
             throw new DefaultException(EMPLOYEE_IS_MINOR.getMsg());

        return genderService.findById(employee.getGenderId())
                .map(genders -> jobService.findById(employee.getJobId())
                            .map(jobs -> {
                                Long id = employeeService.save(employeeMapper.toEntity(employee)).getId();
                                return Utils.buildReponse(id);
                            })
                            .orElseThrow(() -> new NotFoundException(JOB_NOT_EXISTS.getMsg())))
                .orElseThrow(() -> new NotFoundException(GENDERS_NOT_EXISTS.getMsg()));

    }


    public AppResponse recordWorkingHours(RecordWorkingHoursRequest request){


        if(!employeeService.validEmployee(request.getEmployeeId()))
            throw new DefaultException(EMPLOYEE_NOT_EXISTS.getMsg());

        if((LocalDate.now().compareTo(request.getWorkedDate()) < 0))
            throw new DefaultException(LATER_DATE.getMsg());

        Long id =  employeeWorkedHourService.save(workedHoursMapper.dtoToEntity(request)).getId();

        return Utils.buildReponse(id);
    }

    public EmployeeResponse findByJob(JobRequest request) {

        if(!jobService.findById(request.getJodId()).isPresent())
            throw new DefaultException(JOB_NOT_EXISTS.getMsg());

        List<Employees> employees = employeeService.findByJob(request.getJodId());

        return EmployeeResponse.builder()
                .employees(employees)
                .success(Boolean.TRUE).build();
    }
}

package com.ws.business;

import com.ws.config.error.DefaultException;
import com.ws.mapper.EmployeeMapper;
import com.ws.mapper.EmployeeWorkedHoursMapper;
import com.ws.model.Employees;
import com.ws.model.dto.AmountPaymentResponse;
import com.ws.model.dto.AppResponse;
import com.ws.model.dto.EmployeeRequest;
import com.ws.model.dto.EmployeeResponse;
import com.ws.model.dto.JobRequest;
import com.ws.model.dto.RecordWorkingHoursRequest;
import com.ws.model.dto.WorkedHoursRequest;
import com.ws.model.dto.WorkedHoursResponse;
import com.ws.service.EmployeeService;
import com.ws.service.EmployeeWorkedHourService;
import com.ws.service.GenderService;
import com.ws.service.JobService;
import com.ws.util.Utils;
import org.springframework.beans.factory.annotation.Value;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;

import static com.ws.util.ResponseEnum.EMPLOYEE_IS_MINOR;
import static com.ws.util.ResponseEnum.EMPLOYEE_NOT_EXISTS;
import static com.ws.util.ResponseEnum.GENDERS_NOT_EXISTS;
import static com.ws.util.ResponseEnum.JOB_NOT_EXISTS;
import static com.ws.util.ResponseEnum.LATER_DATE;
import static com.ws.util.ResponseEnum.LATER_DATE_TO_ENDDATE;

@RequiredArgsConstructor
public class EmployeeBusiness {

    private Integer SCALE_2 = 2;

    private final EmployeeService employeeService;
    private final GenderService genderService;
    private final JobService jobService;
    private final EmployeeWorkedHourService employeeWorkedHourService;
    private final EmployeeMapper employeeMapper;
    private final EmployeeWorkedHoursMapper workedHoursMapper;

    @Value("${config.age}")
    private int ageLimit;

    public AppResponse saveEmployee(EmployeeRequest employee){

         int age = LocalDate.now().getYear() - employee.getBirthdate().getYear();

         if(age < ageLimit)
             throw new DefaultException(EMPLOYEE_IS_MINOR.getMsg());

        return genderService.findById(employee.getGenderId())
                .map(genders -> jobService.findById(employee.getJobId())
                            .map(jobs -> {
                                Long id = employeeService.save(employeeMapper.toEntity(employee)).getId();
                                return Utils.buildReponse(id);
                            })
                            .orElseThrow(() -> new DefaultException(JOB_NOT_EXISTS.getMsg())))
                .orElseThrow(() -> new DefaultException(GENDERS_NOT_EXISTS.getMsg()));

    }


    public AppResponse recordWorkingHours(RecordWorkingHoursRequest request){

        if(!employeeService.validEmployee(request.getEmployeeId()))
            throw new DefaultException(EMPLOYEE_NOT_EXISTS.getMsg());

        if(compareDate(LocalDate.now(),request.getWorkedDate()))
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


    public WorkedHoursResponse workedHoursByEmployee (WorkedHoursRequest request) {

        if(compareDate(request.getEndDate(),request.getStartDate()))
            throw new DefaultException(LATER_DATE_TO_ENDDATE.getMsg());

        if(!employeeService.validEmployee(request.getEmployeeId()))
            throw new DefaultException(EMPLOYEE_NOT_EXISTS.getMsg());

        BigDecimal hours = employeeWorkedHourService.workedHoursByEmployee(request.getEmployeeId(),
                request.getStartDate(),request.getEndDate());

        return WorkedHoursResponse.builder()
                .totalWorkedHours(hours)
                .success(Boolean.TRUE).build();
    }

    public AmountPaymentResponse totalPaymentsByEmploy (WorkedHoursRequest request) {

        if(compareDate(request.getEndDate(),request.getStartDate()))
            throw new DefaultException(LATER_DATE_TO_ENDDATE.getMsg());

        BigDecimal payment = employeeService.findById(request.getEmployeeId()).flatMap(e ->  jobService.findById(e.getJob().getId())
                .map(j -> {
                    BigDecimal hours = employeeWorkedHourService.workedHoursByEmployee(request.getEmployeeId(),
                        request.getStartDate(),request.getEndDate());
                    return j.getSalary().multiply(hours);
            })).orElseThrow(() -> new DefaultException(EMPLOYEE_NOT_EXISTS.getMsg()));

        return AmountPaymentResponse.builder()
                .payment(payment.setScale(SCALE_2, RoundingMode.HALF_UP))
                .success(Boolean.TRUE).build();
    }




    public boolean compareDate(LocalDate date1 , LocalDate date2){
        return (date1.compareTo(date2) < 0);
    }
}




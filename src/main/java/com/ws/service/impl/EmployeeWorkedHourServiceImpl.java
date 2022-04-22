package com.ws.service.impl;

import com.ws.config.error.DefaultException;
import com.ws.mapper.EmployeeWorkedHoursMapper;
import com.ws.model.EmployeeWorkedHours;
import com.ws.repository.EmployeeWorkedHourRepository;
import com.ws.repository.model.EmployeeWorkedHoursEntity;
import com.ws.service.EmployeeWorkedHourService;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;

import static com.ws.util.ResponseEnum.HOURS_COMPLETED;
import static com.ws.util.ResponseEnum.HOURS_EXECEEDS;
import static com.ws.util.ResponseEnum.TIME_NOT_REGISTER;

@RequiredArgsConstructor
public class EmployeeWorkedHourServiceImpl implements EmployeeWorkedHourService {

    private final EmployeeWorkedHourRepository employeeWorkedHourRepository;
    private final EmployeeWorkedHoursMapper employeeWorkedHoursMapper;

    private final BigDecimal totalHours = new BigDecimal(20);


    @Override
    public EmployeeWorkedHours save(EmployeeWorkedHoursEntity employeeWorkedHours) {

        BigDecimal workedHours =  employeeWorkedHourRepository.sumHours(employeeWorkedHours.getEmployeeId().getId());

        if(workedHours == null)
            workedHours = BigDecimal.ZERO;


        if(workedHours.compareTo(totalHours) == 0)
            throw new DefaultException(HOURS_COMPLETED.getMsg());

        if(workedHours.add(employeeWorkedHours.getWorkedHours()).compareTo(totalHours) == 1)
            throw new DefaultException(HOURS_EXECEEDS.getMsg());


        return  employeeWorkedHoursMapper.toModel(employeeWorkedHourRepository.findByEmployeeId_IdAndAndWorkedDate(employeeWorkedHours.getEmployeeId().getId(),
                employeeWorkedHours.getWorkedDate()).map(worked -> {
                    worked.setWorkedHours(worked.getWorkedHours().add(employeeWorkedHours.getWorkedHours()));
                    return employeeWorkedHourRepository.save(worked);
        }).orElseGet(() -> employeeWorkedHourRepository.save(employeeWorkedHours)));

    }

    @Override
    public BigDecimal workedHoursByEmployee(Long employeeId, LocalDate startDate, LocalDate endDate) {

        BigDecimal hours = employeeWorkedHourRepository.sumHoursStartDateToEndDate(employeeId,startDate,endDate);

        if(hours == null)
            throw new DefaultException(TIME_NOT_REGISTER.getMsg());

        return hours;
    }

}

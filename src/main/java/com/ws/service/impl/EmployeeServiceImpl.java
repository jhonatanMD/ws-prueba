package com.ws.service.impl;

import com.ws.config.error.DefaultException;
import com.ws.mapper.EmployeeMapper;
import com.ws.model.Employees;
import com.ws.repository.EmployeeRepository;
import com.ws.repository.model.EmployeesEntity;
import com.ws.service.EmployeeService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static com.ws.util.ResponseEnum.EMPLOYEE_EXISTS;


@Slf4j
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    @Override
    public Employees save(EmployeesEntity employee) {

        boolean flag = validEmployee(employee.getName(),employee.getLastName());
          if(!flag)
              return employeeMapper.toModel(employeeRepository.save(employee));

        throw new DefaultException(EMPLOYEE_EXISTS.getMsg());
    }

    @Override
    public List<Employees> findAll() {
        return employeeRepository.findAll().stream().map(employeeMapper::toModel).collect(Collectors.toList());
    }

    @Override
    public List<Employees> findByJob(Long jobId) {

        return employeeRepository.findByJobId_Id(jobId)
                .stream().map(employeeMapper::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public Boolean validEmployee(String name, String lastName) {
        return employeeRepository.existsEmployeesEntityByNameAndLastName(name,lastName);
    }

    @Override
    public Boolean validEmployee(Long employeeId) {
        return employeeRepository.findById(employeeId).isPresent();
    }

    @Override
    public Optional<Employees> findById(Long employeeId) {
        return employeeRepository.findById(employeeId).map(employeeMapper::toModel);
    }


}

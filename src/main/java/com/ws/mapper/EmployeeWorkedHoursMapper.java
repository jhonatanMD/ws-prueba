package com.ws.mapper;

import com.ws.model.EmployeeWorkedHours;
import com.ws.model.dto.RecordWorkingHoursRequest;
import com.ws.repository.model.EmployeeWorkedHoursEntity;
import com.ws.repository.model.EmployeesEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring" , imports = EmployeesEntity.class)
public interface EmployeeWorkedHoursMapper {

    EmployeeWorkedHours toModel (EmployeeWorkedHoursEntity entity);
    EmployeeWorkedHoursEntity toEntity (EmployeeWorkedHours model);

    @Mapping(target = "employeeId" ,expression = "java(EmployeesEntity.builder().id(dto.getEmployeeId()).build())")
    EmployeeWorkedHoursEntity dtoToEntity(RecordWorkingHoursRequest dto);

}

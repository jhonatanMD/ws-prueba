package com.ws.mapper;

import com.ws.model.Employees;
import com.ws.model.dto.EmployeeRequest;
import com.ws.repository.model.EmployeesEntity;
import com.ws.repository.model.GendersEntity;
import com.ws.repository.model.JobsEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring" , imports = {GendersEntity.class, JobsEntity.class})
public interface EmployeeMapper {

    @Mappings({
            @Mapping(target = "genderId",expression = "java(GendersEntity.builder().id(request.getGenderId()).build())"),
            @Mapping(target = "jobId",expression = "java(JobsEntity.builder().id(request.getJobId()).build())")
    })
    EmployeesEntity toEntity (EmployeeRequest request);


    @Mappings({
            @Mapping(target = "gender",source = "genderId"),
            @Mapping(target = "job",source = "jobId")
    })
    Employees toModel(EmployeesEntity entity);
}

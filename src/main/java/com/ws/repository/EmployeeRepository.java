package com.ws.repository;

import com.ws.repository.model.EmployeesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<EmployeesEntity,Long> {

    Boolean existsEmployeesEntityByNameAndLastName(String name , String lastName);
    List<EmployeesEntity> findByJobId_Id(Long jobId);
}

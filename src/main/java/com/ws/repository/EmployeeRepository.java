package com.ws.repository;

import com.ws.repository.model.EmployeesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<EmployeesEntity,Long> {

    @Query("SELECT CASE WHEN COUNT(e) > 0 THEN TRUE ELSE FALSE END " +
            "FROM EmployeesEntity  e where upper(e.name) = upper(?1) and upper(e.lastName) = upper( ?2)")
    Boolean existsEmployeesEntityByNameAndLastName(String name , String lastName);
    List<EmployeesEntity> findByJobId_Id(Long jobId);
}

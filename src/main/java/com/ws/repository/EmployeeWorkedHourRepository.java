package com.ws.repository;

import com.ws.repository.model.EmployeeWorkedHoursEntity;
import com.ws.repository.model.EmployeesEntity;
import com.ws.repository.model.TotalHours;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface EmployeeWorkedHourRepository extends JpaRepository<EmployeeWorkedHoursEntity,Long> {


    Optional<EmployeeWorkedHoursEntity> findByEmployeeId_IdAndAndWorkedDate(Long employeeId, LocalDate date);

    @Query("SELECT SUM(e.workedHours) FROM EmployeeWorkedHoursEntity e where e.employeeId.id = ?1 ")
    BigDecimal sumHours(Long employeeId);

    @Query("SELECT SUM(e.workedHours) FROM EmployeeWorkedHoursEntity e where e.employeeId.id = ?1 and( (?3 is not null and e.workedDate BETWEEN ?2 and ?3) or (?3 is  null and e.workedDate = ?2 ))")
    BigDecimal sumHoursStartDateToEndDate(Long employeeId,LocalDate startDate,LocalDate endDate);

}

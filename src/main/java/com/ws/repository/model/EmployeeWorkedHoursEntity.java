package com.ws.repository.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Table(name = "employee_worked_hours")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeWorkedHoursEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    private EmployeesEntity employeeId;

    private BigDecimal workedHours;

    private LocalDate workedDate;
}

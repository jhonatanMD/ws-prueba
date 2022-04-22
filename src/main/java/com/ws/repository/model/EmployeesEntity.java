package com.ws.repository.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Table(name = "employees")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class EmployeesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;


    @Column(name = "last_name")
    private String lastName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "gender_id", referencedColumnName = "id")
    private GendersEntity genderId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "job_id", referencedColumnName = "id")
    private JobsEntity jobId;


    private LocalDate birthdate;

}

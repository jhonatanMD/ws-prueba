package com.ws.model;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Employees {

    private Long id;

    private String name;

    private String lastName;

    private Genders gender;

    private Jobs job;

    private LocalDate birthdate;

}

package com.ws.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseEnum {
    OK("ok"),
    EMPLOYEE_EXISTS("Empleado ya existe"),
    EMPLOYEE_NOT_EXISTS("Empleado no existe"),
    JOB_NOT_EXISTS("Trabajo no existe"),
    GENDERS_NOT_EXISTS("Genero no existe"),
    EMPLOYEE_IS_MINOR("Empleado a registrar es menor de edad"),
    LATER_DATE("La fecha no puede ser posterior a la actual"),
    HOURS_COMPLETED("No se puede agregar mas horas , porque ya tiene las horas completas"),
    HOURS_EXECEEDS("La hora a registrar excede del limite");

    private String msg;



}

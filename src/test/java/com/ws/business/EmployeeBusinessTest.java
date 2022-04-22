package com.ws.business;

import com.ws.config.error.DefaultException;
import com.ws.mapper.EmployeeMapper;
import com.ws.mapper.EmployeeWorkedHoursMapper;
import com.ws.model.Employees;
import com.ws.model.Genders;
import com.ws.model.Jobs;
import com.ws.model.dto.AppResponse;
import com.ws.model.dto.EmployeeRequest;
import com.ws.service.EmployeeService;
import com.ws.service.EmployeeWorkedHourService;
import com.ws.service.GenderService;
import com.ws.service.JobService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDate;
import java.util.Optional;

import static com.ws.util.ResponseEnum.EMPLOYEE_IS_MINOR;
import static com.ws.util.ResponseEnum.GENDERS_NOT_EXISTS;
import static com.ws.util.ResponseEnum.JOB_NOT_EXISTS;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

public class EmployeeBusinessTest {


    @InjectMocks
    private EmployeeBusiness employeeBusiness;
    @Mock
    private  EmployeeService employeeService;
    @Mock
    private  GenderService genderService;
    @Mock
    private  JobService jobService;
    @Mock
    private  EmployeeWorkedHourService employeeWorkedHourService;
    @Mock
    private  EmployeeMapper employeeMapper;
    @Mock
    private  EmployeeWorkedHoursMapper workedHoursMapper;


    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        ReflectionTestUtils.setField(employeeBusiness, "ageLimit", 18);
    }


    @Test
    public void saveEmployeeTestOk(){

        Mockito.when(genderService.findById(any()))
                .thenReturn(Optional.of(Genders.builder().id(1L).name("Hombre").build()));

        Mockito.when(jobService.findById(any()))
                .thenReturn(Optional.of(Jobs.builder().id(1L).name("Developer").build()));

        Mockito.when(employeeService.save(any()))
                .thenReturn(Employees.builder()
                        .id(1L)
                        .build());

       AppResponse test = employeeBusiness.saveEmployee(EmployeeRequest.builder()
                .jobId(1L)
                .genderId(1L)
                .birthdate(LocalDate.of(1999,8,19))
                .lastName("Mallqui Diaz")
                .name("Jhonatan")
                .build());

        Assertions.assertEquals(1L,test.getId());


    }


    @Test
    public void saveEmployeeIsMinorTest(){

        Throwable exception = assertThrows(DefaultException.class, () -> {

            employeeBusiness.saveEmployee(EmployeeRequest.builder()
                    .jobId(1L)
                    .genderId(1L)
                    .birthdate(LocalDate.of(2005,8,19))
                    .lastName("Mallqui Diaz")
                    .name("Jhonatan")
                    .build());
        });

        Assertions.assertEquals(EMPLOYEE_IS_MINOR.getMsg(), exception.getMessage());

    }

    @Test
    public void saveEmployeeGenderNoExitsTest(){

        Mockito.when(genderService.findById(any()))
                .thenReturn(Optional.of(Genders.builder().id(1L).name("Hombre").build()));

        Mockito.when(jobService.findById(any()))
                .thenReturn(Optional.empty());


        Throwable exception = assertThrows(DefaultException.class, () -> {

            employeeBusiness.saveEmployee(EmployeeRequest.builder()
                    .jobId(1L)
                    .genderId(1L)
                    .birthdate(LocalDate.of(1999,8,19))
                    .lastName("Mallqui Diaz")
                    .name("Jhonatan")
                    .build());
        });

        Assertions.assertEquals(JOB_NOT_EXISTS.getMsg(), exception.getMessage());

    }

    @Test
    public void saveEmployeeJobNoExitsTest(){

        Mockito.when(genderService.findById(any()))
                .thenReturn(Optional.empty());


        Throwable exception = assertThrows(DefaultException.class, () -> {

            employeeBusiness.saveEmployee(EmployeeRequest.builder()
                    .jobId(1L)
                    .genderId(1L)
                    .birthdate(LocalDate.of(1999,8,19))
                    .lastName("Mallqui Diaz")
                    .name("Jhonatan")
                    .build());
        });

        Assertions.assertEquals(GENDERS_NOT_EXISTS.getMsg(), exception.getMessage());


    }
}

package com.ws.config;

import com.ws.business.EmployeeBusiness;
import com.ws.mapper.EmployeeMapper;
import com.ws.mapper.EmployeeWorkedHoursMapper;
import com.ws.mapper.GenderMapper;
import com.ws.mapper.JobMapper;
import com.ws.repository.EmployeeRepository;
import com.ws.repository.EmployeeWorkedHourRepository;
import com.ws.repository.GenderRepository;
import com.ws.repository.JobRepository;
import com.ws.service.EmployeeService;
import com.ws.service.EmployeeWorkedHourService;
import com.ws.service.GenderService;
import com.ws.service.JobService;
import com.ws.service.impl.EmployeeServiceImpl;
import com.ws.service.impl.EmployeeWorkedHourServiceImpl;
import com.ws.service.impl.GenderServiceImpl;
import com.ws.service.impl.JobServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfigBean {

    @Bean
    public EmployeeService employeeService(EmployeeRepository employeeRepository,EmployeeMapper employeeMapper){
        return new EmployeeServiceImpl(employeeRepository,employeeMapper);
    }

    @Bean
    public EmployeeBusiness employeeBusiness(EmployeeService employeeService,GenderService genderService,
                                             JobService jobService ,EmployeeWorkedHourService employeeWorkedHourService,
                                             EmployeeMapper employeeMapper, EmployeeWorkedHoursMapper workedHoursMapper){
        return new EmployeeBusiness(employeeService,genderService,jobService,
                employeeWorkedHourService,employeeMapper,workedHoursMapper);
    }

    @Bean
    public JobService jobService (JobRepository jobRepository , JobMapper jobMapper){
        return new JobServiceImpl(jobRepository,jobMapper);
    }

    @Bean
    public GenderService genderService(GenderRepository genderRepository, GenderMapper genderMapper){
        return new GenderServiceImpl(genderRepository,genderMapper);
    }

    @Bean
    public EmployeeWorkedHourService employeeWorkedHourService(EmployeeWorkedHourRepository employeeWorkedHourRepository ,
                                                               EmployeeWorkedHoursMapper employeeWorkedHoursMapper){
        return new EmployeeWorkedHourServiceImpl(employeeWorkedHourRepository,employeeWorkedHoursMapper);
    }

}

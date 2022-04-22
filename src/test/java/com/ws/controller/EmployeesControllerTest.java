package com.ws.controller;

import com.ws.business.EmployeeBusiness;
import com.ws.model.dto.EmployeeRequest;
import com.ws.model.dto.JobRequest;
import com.ws.model.dto.RecordWorkingHoursRequest;
import com.ws.model.dto.WorkedHoursRequest;
import com.ws.util.TestUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmployeesControllerTest {


    @InjectMocks
    private EmployeesController controller;

    @Mock
    private EmployeeBusiness business;

    MockMvc mockMvc;


    @BeforeEach
    void setUp(){

        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(this.controller).build();

    }


    @Test
    public void whenEmployeesSave_thenShouldBeSuccessful() throws Exception {
        MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders.post("/ws/employees/save")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtil.writeDataToJson(EmployeeRequest.builder()
                        .birthdate(LocalDate.of(1999,8,19))
                        .genderId(1L)
                        .jobId(1L)
                        .name("Jhonatan")
                        .lastName("Mallqui")
                        .build())))
                .andDo(MockMvcResultHandlers.print()).andReturn();

        assertEquals(HttpStatus.OK.value(),mvcResult.getResponse().getStatus());
    }

    @Test
    public void whenEmployeesSave_BadRequest() throws Exception {
        MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders.post("/ws/employees/save")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtil.writeDataToJson(EmployeeRequest.builder()
                                .birthdate(LocalDate.of(1999,8,19))
                                .genderId(1L)
                                .jobId(1L)
                                .build())))
                .andDo(MockMvcResultHandlers.print()).andReturn();
        assertEquals(HttpStatus.BAD_REQUEST.value(),mvcResult.getResponse().getStatus());
    }

    @Test
    public void whenAddWorkingHours_thenShouldBeSuccessful() throws Exception {
        MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders.post("/ws/employees/add-working-hours")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtil.writeDataToJson(RecordWorkingHoursRequest.builder()
                                .employeeId(1L)
                                .workedDate(LocalDate.of(2022,04,20))
                                .workedHours(BigDecimal.TEN)
                                .build())))
                .andDo(MockMvcResultHandlers.print()).andReturn();

        assertEquals(HttpStatus.OK.value(),mvcResult.getResponse().getStatus());
    }

    @Test
    public void whenAddWorkingHours_BadRequest() throws Exception {
        MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders.post("/ws/employees/add-working-hours")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtil.writeDataToJson(RecordWorkingHoursRequest.builder()
                                .employeeId(1L)
                                .build())))
                .andDo(MockMvcResultHandlers.print()).andReturn();

        assertEquals(HttpStatus.BAD_REQUEST.value(),mvcResult.getResponse().getStatus());
    }

    @Test
    public void whenFindByJob_thenShouldBeSuccessful() throws Exception {
        MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders.post("/ws/employees/find-by-job")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtil.writeDataToJson(new JobRequest(1L))))
                .andDo(MockMvcResultHandlers.print()).andReturn();

        assertEquals(HttpStatus.OK.value(),mvcResult.getResponse().getStatus());
    }


    @Test
    public void whenFindByJob_BadRequest() throws Exception {
        MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders.post("/ws/employees/find-by-job")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtil.writeDataToJson(new JobRequest())))
                .andDo(MockMvcResultHandlers.print()).andReturn();

        assertEquals(HttpStatus.BAD_REQUEST.value(),mvcResult.getResponse().getStatus());
    }

    @Test
    public void whenWorkedHours_thenShouldBeSuccessful() throws Exception {
        MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders.post("/ws/employees/wordked-hours")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtil.writeDataToJson(WorkedHoursRequest.builder()
                                .employeeId(1L)
                                .startDate(LocalDate.of(2022,02,01))
                                .endDate(LocalDate.of(2022,02,03))
                                .build())))
                .andDo(MockMvcResultHandlers.print()).andReturn();

        assertEquals(HttpStatus.OK.value(),mvcResult.getResponse().getStatus());
    }

    @Test
    public void whenWorkedHours_BadRequest() throws Exception {
        MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders.post("/ws/employees/wordked-hours")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtil.writeDataToJson(WorkedHoursRequest.builder()
                                .employeeId(1L)
                                .build())))
                .andDo(MockMvcResultHandlers.print()).andReturn();

        assertEquals(HttpStatus.BAD_REQUEST.value(),mvcResult.getResponse().getStatus());
    }

    @Test
    public void whenTotalPayment_thenShouldBeSuccessful() throws Exception {
        MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders.post("/ws/employees/total-payment")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtil.writeDataToJson(WorkedHoursRequest.builder()
                                .employeeId(1L)
                                .startDate(LocalDate.of(2022,02,01))
                                .endDate(LocalDate.of(2022,02,03))
                                .build())))
                .andDo(MockMvcResultHandlers.print()).andReturn();

        assertEquals(HttpStatus.OK.value(),mvcResult.getResponse().getStatus());
    }


    @Test
    public void whenTotalPayment_BadRequest() throws Exception {
        MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders.post("/ws/employees/total-payment")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtil.writeDataToJson(WorkedHoursRequest.builder()
                                .employeeId(1L)
                                .build())))
                .andDo(MockMvcResultHandlers.print()).andReturn();

        assertEquals(HttpStatus.BAD_REQUEST.value(),mvcResult.getResponse().getStatus());
    }

}

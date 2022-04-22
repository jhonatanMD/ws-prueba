package com.ws.controller;

import com.ws.mapper.JobMapper;
import com.ws.model.Jobs;
import com.ws.service.JobService;
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

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JobsControllerTest {


    @InjectMocks
    private JobsController controller;

    @Mock
    private JobService jobService;

    @Mock
    private  JobMapper mapper;

    MockMvc mockMvc;


    @BeforeEach
    void setUp(){

        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(this.controller).build();

    }


    @Test
    public void whenJobSave_thenShouldBeSuccessful() throws Exception {
        MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders.post("/ws/jobs/save")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtil.writeDataToJson(Jobs.builder()
                        .name("Developer BackEnd")
                        .salary(BigDecimal.valueOf(250))
                        .build())))
                .andDo(MockMvcResultHandlers.print()).andReturn();

        assertEquals(HttpStatus.OK.value(),mvcResult.getResponse().getStatus());
    }


    @Test
    public void whenJobSave_BadRequest() throws Exception {
        MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders.post("/ws/jobs/save")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtil.writeDataToJson(Jobs.builder()
                                .name("Developer BackEnd")
                                .build())))
                .andDo(MockMvcResultHandlers.print()).andReturn();

        assertEquals(HttpStatus.BAD_REQUEST.value(),mvcResult.getResponse().getStatus());
    }


}

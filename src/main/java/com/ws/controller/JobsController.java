package com.ws.controller;

import com.ws.mapper.JobMapper;
import com.ws.model.Jobs;
import com.ws.service.JobService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/ws/jobs")
@RequiredArgsConstructor
public class JobsController {

    private final JobService jobService;
    private final JobMapper mapper;

    @PostMapping("/save")
    public Jobs saveJob(@RequestBody @Valid Jobs job){
        return jobService.save(mapper.toEntity(job));
    }

}

package com.ws.service.impl;

import com.ws.mapper.JobMapper;
import com.ws.model.Jobs;
import com.ws.repository.JobRepository;
import com.ws.repository.model.JobsEntity;
import com.ws.service.JobService;

import java.util.Optional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;
    private final JobMapper jobMapper;

    @Override
    public Optional<Jobs> findById(Long id) {
        return jobRepository.findById(id).map(jobMapper::toModel);
    }

    @Override
    public Jobs save(JobsEntity jobs) {
        return jobMapper.toModel(jobRepository.save(jobs));
    }
}

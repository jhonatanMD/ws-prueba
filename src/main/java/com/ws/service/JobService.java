package com.ws.service;

import com.ws.model.Jobs;
import com.ws.repository.model.JobsEntity;

import java.util.Optional;

public interface JobService {

    Optional<Jobs> findById(Long id);

    Jobs save(JobsEntity jobs);

}

package com.ws.service;

import com.ws.model.Jobs;

import java.util.Optional;

public interface JobService {

    Optional<Jobs> findById(Long id);
}

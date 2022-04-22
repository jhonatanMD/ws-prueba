package com.ws.service;

import com.ws.model.Genders;

import java.util.Optional;

public interface GenderService {


    Optional<Genders> findById(Long id);

}

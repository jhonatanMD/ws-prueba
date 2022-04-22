package com.ws.service.impl;

import com.ws.mapper.GenderMapper;
import com.ws.model.Genders;
import com.ws.repository.GenderRepository;
import com.ws.service.GenderService;

import java.util.Optional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GenderServiceImpl implements GenderService {

    private final GenderRepository genderRepository;
    private final GenderMapper genderMapper;

    @Override
    public Optional<Genders> findById(Long id) {
        return genderRepository.findById(id).map(genderMapper::toModel);
    }
}

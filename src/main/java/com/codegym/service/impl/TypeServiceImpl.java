package com.codegym.service.impl;

import com.codegym.model.Type;
import com.codegym.repository.TypeRepository;
import com.codegym.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class TypeServiceImpl implements TypeService {
    @Autowired
    TypeRepository typeRepository;
    @Override
    public Page<Type> findAll(Pageable pageable) {
        return typeRepository.findAll(pageable);
    }
}

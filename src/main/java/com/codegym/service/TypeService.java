package com.codegym.service;

import com.codegym.model.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TypeService {
    Page<Type> findAll(Pageable pageable);
}

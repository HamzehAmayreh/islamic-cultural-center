package com.ju.islamicculturalcenter.service;

import com.ju.islamicculturalcenter.dto.BaseRequestDto;
import com.ju.islamicculturalcenter.dto.BaseResponseDto;
import com.ju.islamicculturalcenter.entity.BaseEntity;

import java.util.List;

public interface IBaseService<T extends BaseEntity, R extends BaseRequestDto, S extends BaseResponseDto, ID> {

    List<S> findAllByActive(Boolean active);

    S findById(ID Id);

    void save(R obj);

    S update(R obj);

    void deleteById(ID Id);
}

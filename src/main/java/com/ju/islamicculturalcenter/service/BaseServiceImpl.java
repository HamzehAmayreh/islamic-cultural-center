package com.ju.islamicculturalcenter.service;

import com.ju.islamicculturalcenter.dto.BaseRequestDto;
import com.ju.islamicculturalcenter.dto.BaseResponseDto;
import com.ju.islamicculturalcenter.entity.BaseEntity;
import com.ju.islamicculturalcenter.repos.BaseRepo;

import java.util.List;

public abstract class BaseServiceImpl<T extends BaseEntity, R extends BaseRequestDto, S extends BaseResponseDto, ID> implements IBaseService<T,R,S,ID>{

    @Override
    public List<S> findAllByActive(Boolean active) {
        return null;
    }

    @Override
    public S findById(ID Id) {
        return null;
    }

    @Override
    public void save(R obj) {

    }

    @Override
    public S update(R obj) {
        return null;
    }

    @Override
    public void deleteById(ID Id) {

    }

    public abstract BaseRepo<T,ID> getRepo();
}

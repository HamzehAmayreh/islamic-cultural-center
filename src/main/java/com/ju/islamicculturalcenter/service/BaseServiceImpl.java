package com.ju.islamicculturalcenter.service;

import com.ju.islamicculturalcenter.dto.BaseRequestDto;
import com.ju.islamicculturalcenter.dto.BaseResponseDto;
import com.ju.islamicculturalcenter.entity.BaseEntity;
import com.ju.islamicculturalcenter.mappers.BaseMapper;
import com.ju.islamicculturalcenter.repos.BaseRepo;

import java.util.List;
import java.util.stream.Collectors;

public abstract class BaseServiceImpl<T extends BaseEntity, R extends BaseRequestDto, S extends BaseResponseDto> implements IBaseService<T,R,S>{

    @Override
    public List<S> findAllByActive(Boolean active) {
        return getRepo().findAllByIsActive(active).stream()
                .map(getMapper()::mapEntityToDto).collect(Collectors.toList());
    }

    @Override
    public S findById(Long id, Boolean active) {
        return getMapper().mapEntityToDto(getRepo().findByIdAndIsActive(id,active));
    }

    @Override
    public void save(R dto) {
        getRepo().save(getMapper().mapDtoToEntity(dto));
    }

    @Override
    public S update(R dto) {
        //TODO
        return null;
    }

    @Override
    public void deleteById(Long id) {
        getRepo().softDelete(id);
    }

    public abstract BaseRepo<T,Long> getRepo();

    public abstract BaseMapper<T,R,S> getMapper();
}

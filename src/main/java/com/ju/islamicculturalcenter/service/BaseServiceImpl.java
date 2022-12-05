package com.ju.islamicculturalcenter.service;

import com.ju.islamicculturalcenter.dto.BaseRequestDto;
import com.ju.islamicculturalcenter.dto.BaseResponseDto;
import com.ju.islamicculturalcenter.entity.BaseEntity;
import com.ju.islamicculturalcenter.exceptions.NotFoundException;
import com.ju.islamicculturalcenter.mappers.BaseMapper;
import com.ju.islamicculturalcenter.repos.BaseRepo;

import java.util.List;
import java.util.stream.Collectors;

public abstract class BaseServiceImpl<T extends BaseEntity, R extends BaseRequestDto, S extends BaseResponseDto, U extends BaseRequestDto> implements IBaseService<T,R,S,U>{

    @Override
    public List<S> findAllByActive(Boolean active) {
        return getRepo().findAllByIsActive(active).stream() //stream is like for loop but is faster having parallel loops and return the element in collection
                .map(getMapper()::mapEntityToDto)// map method loops through every element of the return type
                .collect(Collectors.toList());
    }

    @Override
    public S findById(Long id, Boolean active) {
        return getMapper().mapEntityToDto(getRepo().findByIdAndIsActive(id,active)
                .orElseThrow(() -> new NotFoundException("No Entity Found with ID: " + id)));
    }

    @Override
    public S save(R dto) {
        preSave(dto);
        return getMapper().mapEntityToDto(getRepo().save(getMapper().mapDtoToEntity(dto)));
    }

    @Override
    public S update(Long id, U dto) {
        T t = getRepo().findByIdAndIsActive(id, true)
                .orElseThrow(() -> new NotFoundException("No Entity Found with ID: " + id));

        T save = getRepo().save(updateEntity(t, dto));

        return getMapper().mapEntityToDto(save);
    }

    @Override
    public void deleteById(Long id) {
        getRepo().softDelete(id);
    }

    public abstract T updateEntity(T entity, U dto);

    public abstract BaseRepo<T,Long> getRepo(); // to be implemented by every class that extends this class [return the repo of the entity the service implements]

    public abstract BaseMapper<T,R,S> getMapper(); // to be implemented by every class that extends this class [return the mapper of the dto's the service implements]

    public void preSave(R requestDto){

    }

    public void postSave(T entity){

    }
}

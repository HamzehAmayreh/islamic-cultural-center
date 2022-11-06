package com.ju.islamicculturalcenter.service.iservice;

import com.ju.islamicculturalcenter.entity.Base;

import java.util.List;

public interface IBaseService<T extends Base, ID> {

    List<T> findAll();

    T findById(ID Id);

    void save(T obj);

    T update(T obj);

    void deleteById(ID Id);
}

package com.ju.islamicculturalcenter.repos;

import com.ju.islamicculturalcenter.entity.Base;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface BaseRepo<T extends Base, ID> extends JpaRepository<T, ID> {

    List<T> findAllByIsActive(Boolean isActive);

    T findByIdAndIsActive(Long id, Boolean isActive);
}
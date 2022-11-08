package com.ju.islamicculturalcenter.repos;

import com.ju.islamicculturalcenter.entity.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

import java.util.List;

@NoRepositoryBean
public interface BaseRepo<T extends BaseEntity, ID> extends JpaRepository<T, ID> {

    List<T> findAllByIsActive(Boolean isActive);

    T findByIdAndIsActive(Long id, Boolean isActive);

    @Modifying
    @Query("UPDATE #{#entityName} t SET t.isActive = true WHERE t.id = :id")
    void softDelete(@Param("id") Long id);
}

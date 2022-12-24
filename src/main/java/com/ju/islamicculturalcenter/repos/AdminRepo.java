package com.ju.islamicculturalcenter.repos;

import com.ju.islamicculturalcenter.entity.AdminEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminRepo extends BaseRepo<AdminEntity, Long> {

    List<AdminEntity> findAllByUser_FirstNameOrUser_LastNameLike(String name);
}

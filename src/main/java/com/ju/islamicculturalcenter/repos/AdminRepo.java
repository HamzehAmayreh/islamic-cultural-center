package com.ju.islamicculturalcenter.repos;

import com.ju.islamicculturalcenter.entity.AdminEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepo extends BaseRepo<AdminEntity, Long> {
}

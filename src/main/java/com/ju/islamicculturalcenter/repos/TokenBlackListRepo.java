package com.ju.islamicculturalcenter.repos;

import com.ju.islamicculturalcenter.entity.TokenBlackListEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TokenBlackListRepo extends  BaseRepo<TokenBlackListEntity, Long> {

    List<TokenBlackListEntity> findAllByToken(String token);
}

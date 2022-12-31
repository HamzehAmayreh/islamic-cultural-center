package com.ju.islamicculturalcenter.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "token_black_list")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TokenBlackListEntity extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "token", nullable = false, length = 2000)
    private String token;

    @Builder
    public TokenBlackListEntity(Timestamp creationDate, Long createdById, Timestamp updateDate, Long updatedById, Boolean active, Long id, String token) {
        super(creationDate, createdById, updateDate, updatedById, active);
        this.id = id;
        this.token = token;
    }
}

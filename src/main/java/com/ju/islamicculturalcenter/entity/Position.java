package com.ju.islamicculturalcenter.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Table
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Position extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Builder
    public Position(Timestamp creation_Date, Long createdById, Timestamp updateDate, Long updatedById, Boolean active, Long id, String name) {
        super(creation_Date, createdById, updateDate, updatedById, active);
        this.id = id;
        this.name = name;
    }
}

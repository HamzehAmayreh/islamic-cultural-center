package com.ju.islamicculturalcenter.entity;

import lombok.*;

import javax.persistence.*;

@Table
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Position extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;


    public Position(String creation_Date, String createdById, String updateDate, String updatedById, boolean active, Long id, String name) {
        super(creation_Date, createdById, updateDate, updatedById, active);
        this.id = id;
        this.name = name;
    }
}

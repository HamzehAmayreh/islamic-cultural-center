package com.ju.islamicculturalcenter.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Table(name = "material")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Material extends Base{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "url", nullable = false)
    private String url;

    @Column(name = "year", nullable = false)
    private Date year;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "instructor_id",referencedColumnName = "id")
    private Instructor instructor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id",referencedColumnName = "id")
    private Course course;

    @Builder
    public Material(Timestamp creation_Date, Long createdById, Timestamp updateDate, Long updatedById, Boolean active, Long id, String url, Date year, Instructor instructor, Course course) {
        super(creation_Date, createdById, updateDate, updatedById, active);
        this.id = id;
        this.url = url;
        this.year = year;
        this.instructor = instructor;
        this.course = course;
    }
}

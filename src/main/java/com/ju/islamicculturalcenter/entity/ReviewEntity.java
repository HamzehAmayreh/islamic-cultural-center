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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "reviews")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReviewEntity extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "rating", nullable = false)
    private Integer rating;

    @Column(name = "comment", length = 2000)
    private String comment;

    @ManyToOne
    @JoinColumn(name = "instructor_id", nullable = false)
    private InstructorEntity instructor;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private StudentEntity student;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private CourseEntity course;

    @Builder
    public ReviewEntity(Timestamp creationDate, Long createdById, Timestamp updateDate, Long updatedById, Boolean active, Long id, Integer rating, String comment, InstructorEntity instructor, StudentEntity student, CourseEntity course) {
        super(creationDate, createdById, updateDate, updatedById, active);
        this.id = id;
        this.rating = rating;
        this.comment = comment;
        this.instructor = instructor;
        this.student = student;
        this.course = course;
    }
}

package com.ju.islamicculturalcenter.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Table(name = "student_courses")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class StudentCoursesEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "paid", nullable = false)
    private Boolean paid;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    private CourseEntity course;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private StudentEntity student;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "instructor_id", referencedColumnName = "id")
    private InstructorEntity instructor;

    @Builder
    public StudentCoursesEntity(Timestamp creationDate, Long createdById, Timestamp updateDate, Long updatedById, Boolean active, Long id, Boolean paid, CourseEntity course, StudentEntity student, InstructorEntity instructor) {
        super(creationDate, createdById, updateDate, updatedById, active);
        this.id = id;
        this.paid = paid;
        this.course = course;
        this.student = student;
        this.instructor = instructor;
    }
}

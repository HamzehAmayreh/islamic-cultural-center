package com.ju.islamicculturalcenter.entity;

import com.ju.islamicculturalcenter.entity.enums.DaysOfWeek;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


@Table(name = "course")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Course extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "course_name", nullable = false, unique = true)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "durtaion", nullable = false)
    private Double duration;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "lecture_time")
    private String lectureTime;

    @Column(name = "days_of_week")
    @ElementCollection
    private List<DaysOfWeek> daysOfWeek;

    @Column(name = "category", nullable = false)
    private String category;

    @Column(name = "max_participants", nullable = false)
    private Integer maxParticipants;

    @Column(name = "is_prerecorded")
    private Boolean isPreRecorded;

    @Column(name = "is_online")
    private Boolean isOnline;

    @Column(name = "is_free", nullable = false)
    private Boolean isFree;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "classroom")
    private String classroom;

    @Column(name = "semester")
    private String semester;

    @Column(name = "year", nullable = false)
    private Integer year;

    @Column(name = "teams_link")
    private String teams_link;

    @Column(name = "last_reg_day", nullable = false)
    private String lastRegDay;

    @Builder
    public Course(Timestamp creation_Date, Long createdById, Timestamp updateDate, Long updatedById, Boolean active, Long id, String name, String description, Double duration, Date startDate, Date endDate, String lectureTime, List<DaysOfWeek> daysOfWeek, String category, Integer maxParticipants, Boolean isPreRecorded, Boolean isOnline, Boolean isFree, Double price, String classroom, String semester, Integer year, String teams_link, String lastRegDay) {
        super(creation_Date, createdById, updateDate, updatedById, active);
        this.id = id;
        this.name = name;
        this.description = description;
        this.duration = duration;
        this.startDate = startDate;
        this.endDate = endDate;
        this.lectureTime = lectureTime;
        this.daysOfWeek = daysOfWeek;
        this.category = category;
        this.maxParticipants = maxParticipants;
        this.isPreRecorded = isPreRecorded;
        this.isOnline = isOnline;
        this.isFree = isFree;
        this.price = price;
        this.classroom = classroom;
        this.semester = semester;
        this.year = year;
        this.teams_link = teams_link;
        this.lastRegDay = lastRegDay;
    }
}

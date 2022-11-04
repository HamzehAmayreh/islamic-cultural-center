package com.ju.islamicculturalcenter.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Course extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    private long courseId;
    
    @Column(name = "course_name")
    private String name;

    @Column(name="description")
    private String description;

    @Column(name="durtaion")
    private double duration;

    @Column(name="start_date")
    private String startDate;
    
    @Column(name="end_date")
    private String endDate;

    @Column(name="lecture_time")
    private String lectureTime;    

    @Column(name = "days_of_week")
    private String []daysOfWeek;
    
    @Column(name = "category")
    private String category;
    
    @Column(name = "max_participants")
    private int maxParticipants;
    
    @Column(name = "is_prerecorded")
    private boolean isPreRecorded;

    @Column(name = "is_online")
    private boolean isOnline;

    @Column(name = "is_free")
    private boolean isFree;
    
    @Column(name = "price")
    private double price;
    
    @Column(name = "classroom")
    private String classroom;
    
    @Column(name = "semester")
    private String semester ;
    
    @Column(name = "year")
    private int year;

    @Column(name = "teams_link")
    private String teams_link;

    @Column(name ="last_reg_day")
    private String lastRegDay;
	
	
	public Course(String creation_Date, String createdById, String updateDate, String updatedById, boolean active,
			long courseId, String name, String description, double duration, String startDate, String endDate,
			String lectureTime, String[] daysOfWeek, String category, int maxParticipants, boolean isPreRecorded,
			boolean isOnline, boolean isFree, double price, String classroom, String semester, int year,
			String teams_link, String lastRegDay) {
		super(creation_Date, createdById, updateDate, updatedById, active);
		this.courseId = courseId;
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

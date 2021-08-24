package com.elp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Coursetable")
public class Course {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int courseId;

	@Column
	private String courseName;

	@Column
	private int fee;
	
    @Column 
	private int duration;
	
    @Column
	private int rating;

	//@ManyToOne
	private int trainerId;
	/*@ManyToOne
	@JoinColumn(name="userId")
	private Trainer trainerId;*/

	@Column
	private String description;

	@Column
	private String category;

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public int getFee() {
		return fee;
	}

	public void setFee(int fee) {
		this.fee = fee;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public int getTrainerId() {
		return trainerId;
	}

	public void setTrainerId(int userId) {
		this.trainerId = userId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Course() {
		super();
	}

	@Override
	public String toString() {
		return "Course [courseId=" + courseId + ", courseName=" + courseName + ", fee=" + fee + ", duration=" + duration
				+ ", rating=" + rating + ", trainerId=" +  trainerId + ", description=" + description + ", category="
				+ category + "]";
	}

	public Course(int courseId, String courseName, int fee, int duration, int rating, int userId, String description,
			String category) {
		super();
		this.courseId = courseId;
		this.courseName = courseName;
		this.fee = fee;
		this.duration = duration;
		this.rating = rating;
		this.trainerId = userId;
		this.description = description;
		this.category = category;
	}
}
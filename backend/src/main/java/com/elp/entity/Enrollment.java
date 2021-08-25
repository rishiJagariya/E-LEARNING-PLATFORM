package com.elp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author Roli Rai
 *
 */
@Entity
public class Enrollment {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int registrationId;
    
   // @OneToOne(mappedBy = "Student")
   // @JoinColumn(name="studentId")
    private int studentId;
    
   // @OneToOne(mappedBy = "Course")
   // @JoinColumn(name="courseId")
    private int courseId;
    
    @Column
    private String dateOfEnroll;
    
    @Column
    private String dateOfCompletion;

	public Enrollment() {
		super();
	}

	public Enrollment(int registrationId, int studentId, int courseId, String dateOfEnroll, String dateOfCompletion) {
		super();
		this.registrationId = registrationId;
		this.studentId = studentId;
		this.courseId = courseId;
		this.dateOfEnroll = dateOfEnroll;
		this.dateOfCompletion = dateOfCompletion;
	}

	public int getRegistrationId() {
		return registrationId;
	}

	public void setRegistrationId(int registrationId) {
		this.registrationId = registrationId;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public String getDateOfEnroll() {
		return dateOfEnroll;
	}

	public void setDateOfEnroll(String dateOfEnroll) {
		this.dateOfEnroll = dateOfEnroll;
	}

	public String getDateOfCompletion() {
		return dateOfCompletion;
	}

	public void setDateOfCompletion(String dateOfCompletion) {
		this.dateOfCompletion = dateOfCompletion;
	}

	@Override
	public String toString() {
		return "Enrollment [registrationId=" + registrationId + ", studentId=" + studentId + ", courseId=" + courseId
				+ ", dateOfEnroll=" + dateOfEnroll + ", dateOfCompletion=" + dateOfCompletion + "]";
	}   
}
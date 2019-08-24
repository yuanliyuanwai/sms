package cn.edu.bit.sms.score.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import cn.edu.bit.sms.common.entity.BaseEntity;
import cn.edu.bit.sms.system.entity.Student;

@Entity
@Table(name = "sco_student_course")
public class StudentCourse extends BaseEntity {
	
	private Student student;
	
	private Course course;
	
	private Integer score;

	@ManyToOne
	@JoinColumn(name = "student_id", nullable = false)
	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	@ManyToOne
	@JoinColumn(name = "course_id", nullable = false)
	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

}

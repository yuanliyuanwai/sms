package cn.edu.bit.sms.score.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import cn.edu.bit.sms.common.entity.BaseEntity;
import cn.edu.bit.sms.system.entity.Teacher;

@Entity
@Table(name = "sco_course")
public class Course extends BaseEntity {
	
	private String name;
	
	private String no;
	
	private Teacher teacher;
	
	private String address;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	@ManyToOne
	@JoinColumn(name = "teacher_id", nullable = false)
	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	

}

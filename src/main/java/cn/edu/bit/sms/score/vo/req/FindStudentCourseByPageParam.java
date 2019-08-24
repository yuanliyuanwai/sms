package cn.edu.bit.sms.score.vo.req;

import cn.edu.bit.sms.common.vo.req.PageParam;

public class FindStudentCourseByPageParam extends PageParam {
	
	private String studentName;
	
	private String teacherName;
	
	private String courseName;

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

}

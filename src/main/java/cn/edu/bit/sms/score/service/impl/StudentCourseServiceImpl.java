package cn.edu.bit.sms.score.service.impl;

import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import cn.edu.bit.sms.common.dto.QueryResult;
import cn.edu.bit.sms.common.hibernate.dto.QueryCondition;
import cn.edu.bit.sms.common.service.BaseServiceImpl;
import cn.edu.bit.sms.common.util.StringUtil;
import cn.edu.bit.sms.score.dao.StudentCourseDao;
import cn.edu.bit.sms.score.entity.Course;
import cn.edu.bit.sms.score.entity.StudentCourse;
import cn.edu.bit.sms.score.service.StudentCourseService;
import cn.edu.bit.sms.score.vo.req.FindStudentCourseByPageParam;
@Service
public class StudentCourseServiceImpl extends BaseServiceImpl<StudentCourse, StudentCourseDao> implements StudentCourseService {

	@Override
	public QueryResult<StudentCourse> findByPage(FindStudentCourseByPageParam param, String student) {
		QueryCondition studentCourseCondition = new QueryCondition();
		studentCourseCondition.setStart(param.getStart());
		studentCourseCondition.setLimit(param.getLimit());
		if (!StringUtil.isNil(param.getTeacherName())) {
			studentCourseCondition.addAlias("course", "course");
			studentCourseCondition.addAlias("course.teacher", "teacher");
			studentCourseCondition.addAlias("teacher.user", "teacherUser");
			studentCourseCondition.addCriterion(Restrictions.like("teacherUser.name", param.getTeacherName(), MatchMode.ANYWHERE));
		}
		if (!StringUtil.isNil(param.getStudentName())) {
			studentCourseCondition.addAlias("student", "student");
			studentCourseCondition.addAlias("student.user", "studentUser");
			studentCourseCondition.addCriterion(Restrictions.like("studentUser.name", param.getStudentName(), MatchMode.ANYWHERE));
		}
		if (!StringUtil.isNil(param.getCourseName())) {
			studentCourseCondition.addAlias("course", "course");
			studentCourseCondition.addCriterion(Restrictions.like("course.name", param.getCourseName(), MatchMode.ANYWHERE));
		}
		if (!StringUtil.isNil(student)) {
			studentCourseCondition.addAlias("student", "student");
			studentCourseCondition.addAlias("student.user", "studentUser");
			studentCourseCondition.addCriterion(Restrictions.like("studentUser.username", student, MatchMode.ANYWHERE));
		}
		QueryResult<StudentCourse> studentCourse = super.dao.findList(studentCourseCondition);
		return studentCourse;
	}
	
}


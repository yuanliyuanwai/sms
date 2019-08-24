package cn.edu.bit.sms.score.service.impl;

import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import cn.edu.bit.sms.common.dto.QueryResult;
import cn.edu.bit.sms.common.hibernate.dto.QueryCondition;
import cn.edu.bit.sms.common.service.BaseServiceImpl;
import cn.edu.bit.sms.common.util.StringUtil;
import cn.edu.bit.sms.score.dao.CourseDao;
import cn.edu.bit.sms.score.entity.Course;
import cn.edu.bit.sms.score.service.CourseService;
import cn.edu.bit.sms.score.vo.req.FindScoreByPageParam;

@Service
public class CourseServiceImpl extends BaseServiceImpl<Course, CourseDao> implements CourseService {

	@Override
	public QueryResult<Course> findByPage(FindScoreByPageParam param) {
		QueryCondition courseCondition = new QueryCondition();
		courseCondition.setStart(param.getStart());
		courseCondition.setLimit(param.getLimit());
		if (!StringUtil.isNil(param.getName())) {
			courseCondition.addCriterion(Restrictions.like("name", param.getName(), MatchMode.ANYWHERE));
		}
		if (!StringUtil.isNil(param.getNo())) {
			courseCondition.addCriterion(Restrictions.like("no", param.getNo(), MatchMode.ANYWHERE));
		}
		QueryResult<Course> teacherResult = super.dao.findList(courseCondition);
		return teacherResult;
	}
	
}

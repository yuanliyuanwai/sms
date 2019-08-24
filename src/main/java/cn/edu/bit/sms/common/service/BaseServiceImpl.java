package cn.edu.bit.sms.common.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import cn.edu.bit.sms.common.dao.IBaseDao;
import cn.edu.bit.sms.common.dto.QueryResult;
import cn.edu.bit.sms.common.entity.BaseEntity;
import cn.edu.bit.sms.common.hibernate.dto.QueryCondition;

public class BaseServiceImpl<T extends BaseEntity, DAO extends IBaseDao<T>> implements BaseService<T> {

    @Autowired
    protected DAO dao;

    @Override
    public T get(Serializable id) {
        return dao.get(id);
    }
    
    @Override
	public List<T> find(Serializable... ids) {
		return dao.find(ids);
	}

    @Override
    public Long save(T entity) {
        return dao.save(entity);
    }

    @Override
    public void update(T entity) {
        dao.update(entity);
    }

    @Override
    public void delete(Serializable id) {
        dao.delete(id);
    }

    @Override
    public List<T> findAll() {
        return dao.findAll();
    }
    
    @Override
    public <M> QueryResult<M> findList(QueryCondition condition) {
        return dao.findList(condition);
    }

    @Override
    public <M> M findUnique(QueryCondition condition) {
        return dao.findUnique(condition);
    }

    @Override
    public Integer update(String hql, Map<String, Object> parameters) {
        return dao.update(hql, parameters);
    }

	@Override
	public <M> List<M> findBySql(String sql, Object... params) {
		return dao.findBySql(sql, params);
	}

}

package cn.edu.bit.sms.common.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import cn.edu.bit.sms.common.dto.QueryResult;
import cn.edu.bit.sms.common.entity.BaseEntity;
import cn.edu.bit.sms.common.hibernate.dto.QueryCondition;

public interface IBaseDao<T extends BaseEntity> {

    T get(Serializable id);
    
	List<T> find(Serializable... ids);

    Long save(T entity);

    void update(T entity);

    void delete(Serializable id);

    List<T> findAll();
    
    <M> QueryResult<M> findList(QueryCondition condition);

    <M> M findUnique(QueryCondition condition);

    Integer update(final String hql, final Map<String, Object> parameters);

    List<T> findByHql(String hql, Object... values);
    
    <M> List<M> findBySql(final String sql, final Object... params);
}

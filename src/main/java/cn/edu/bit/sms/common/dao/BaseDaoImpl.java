package cn.edu.bit.sms.common.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.HibernateTemplate;

import cn.edu.bit.sms.common.dto.QueryResult;
import cn.edu.bit.sms.common.entity.BaseEntity;
import cn.edu.bit.sms.common.hibernate.dto.QueryCondition;
import cn.edu.bit.sms.common.hibernate.util.HibernateQueryUtil;

public class BaseDaoImpl<T extends BaseEntity> implements IBaseDao<T> {

    private Class<T> entityClass;

    @Resource(name = "hibernateTemplate")
    protected HibernateTemplate hibernateTemplate;

    @SuppressWarnings("unchecked")
    public BaseDaoImpl() {
        ParameterizedType parameterizedType = (ParameterizedType) (getClass().getGenericSuperclass());
        Type type = parameterizedType.getActualTypeArguments()[0];
        if (type instanceof ParameterizedType) {
            type = ((ParameterizedType) type).getRawType();
        }
        this.entityClass = (Class<T>) type;
    }

    public Class<T> getEntityClass() {
        return entityClass;
    }

    public <M> QueryResult<M> findList(QueryCondition condition) {
        Criteria criteria = hibernateTemplate.getSessionFactory().getCurrentSession().createCriteria(this.entityClass);
        return HibernateQueryUtil.queryList(criteria, condition);
    }

    public T get(Serializable id) {
        return (T) hibernateTemplate.get(entityClass, id);
    }
    
    public List<T> find(Serializable... ids) {
    	QueryCondition condition = new QueryCondition();
    	condition.addCriterion(Restrictions.in("id", ids));
    	QueryResult<T> result = findList(condition);
    	return result.getList();
    }

    public void saveOrUpdate(T entity) {
        hibernateTemplate.saveOrUpdate(entity);
    }

    public void delete(Serializable id) {
        T t = hibernateTemplate.get(entityClass, id);
        hibernateTemplate.delete(t);
    }

    public <M> M findUnique(QueryCondition condition) {
        Criteria criteria = hibernateTemplate.getSessionFactory().getCurrentSession().createCriteria(this.entityClass);
        return HibernateQueryUtil.queryUnique(criteria, condition);
    }

    public Integer update(final String hql, final Map<String, Object> parameters) {
        return this.hibernateTemplate.execute(new HibernateCallback<Integer>() {

            @Override
            public Integer doInHibernate(Session session) throws HibernateException {
                Query query = session.createQuery(hql);
                if (parameters != null) {
                    for (Map.Entry<String, Object> parameter : parameters.entrySet()) {
                        if (parameter.getValue() instanceof Collection<?>) {
                            query.setParameterList(parameter.getKey(), (Collection<?>) parameter.getValue());
                        } else {
                            query.setParameter(parameter.getKey(), parameter.getValue());
                        }
                    }
                }
                return query.executeUpdate();
            }
        });
    }

    @Override
    public Long save(T entity) {
        return (Long) hibernateTemplate.save(entity);
    }

    @Override
    public void update(T entity) {
        hibernateTemplate.update(entity);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> findAll() {
        String hql = "from " + getEntityClass().getSimpleName();
        return (List<T>) hibernateTemplate.find(hql);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> findByHql(String hql, Object... values) {
        return (List<T>) hibernateTemplate.find(hql, values);
    }
    
    @Override
    public <M> List<M> findBySql(final String sql, final Object... params) {
    	// 处理参数为集合的情况
    	// 找出所有的?替换成:parameter1
    	String tempSql = sql;
    	final Map<String, Object> parameters = new HashMap<String, Object>();
    	int i = 0;
		while (tempSql.contains("?")) {
			String key = "pamater" + i;
			tempSql = tempSql.replaceFirst("\\?", ":" + key);
			parameters.put(key, params[i]);
			i++;
		}
		final String lastSql = tempSql;
        return hibernateTemplate.execute(new HibernateCallback<List<M>>() {
        	@SuppressWarnings("unchecked")
			@Override
            public List<M> doInHibernate(Session session) throws HibernateException {
                Query query = session.createSQLQuery(lastSql);
                if (parameters != null && parameters.size() > 0) {
                    for (Map.Entry<String, Object> parameter : parameters.entrySet()) {
                        if (parameter.getValue() instanceof Collection<?>) {
                            query.setParameterList(parameter.getKey(), (Collection<?>) parameter.getValue());
                        } else {
                            query.setParameter(parameter.getKey(), parameter.getValue());
                        }
                    }
                }
                return (List<M>) query.list();
            }
        });
    }

}
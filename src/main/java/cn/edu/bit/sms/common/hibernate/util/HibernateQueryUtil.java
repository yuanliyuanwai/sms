package cn.edu.bit.sms.common.hibernate.util;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.InExpression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;

import cn.edu.bit.sms.common.dto.QueryResult;
import cn.edu.bit.sms.common.hibernate.constant.FunctionEnum;
import cn.edu.bit.sms.common.hibernate.constant.OrderEnum;
import cn.edu.bit.sms.common.hibernate.dto.FunctionInvoke;
import cn.edu.bit.sms.common.hibernate.dto.PropertyOrder;
import cn.edu.bit.sms.common.hibernate.dto.QueryCondition;
import cn.edu.bit.sms.common.hibernate.dto.TableAlias;
import cn.edu.bit.sms.common.util.ArrayUtil;
import cn.edu.bit.sms.common.util.CollectionUtil;
import cn.edu.bit.sms.common.util.ObjectUtil;

/**
 * 封装hibernate查询的公用逻辑
 * 
 * @author zhengchong.wan
 *
 */
public class HibernateQueryUtil {

    @SuppressWarnings("unchecked")
    public static <T> T queryUnique(Criteria criteria, QueryCondition condition) {
        if (!buildCriteria(criteria, condition)) return null;
        buildProjection(criteria, condition.getFunctions());
        return (T) criteria.uniqueResult();
    }

    @SuppressWarnings("unchecked")
    public static <T> QueryResult<T> queryList(Criteria criteria, QueryCondition condition) {
        if (condition == null)  condition = new QueryCondition();
        if (!buildCriteria(criteria, condition)) return new QueryResult<T>();
        // 没有使用分页功能
        if (condition.getLimit() <= 0 && condition.getStart() <= 0) {
            buildProjection(criteria, condition.getFunctions());
            buildOrder(criteria, condition.getOrders());
            List<T> list = (List<T>) criteria.list();
            return new QueryResult<T>(list.size(), list);
        } else {
            // 查询总的记录数使用了某些函数后会导致总记录数不对需要再次查询总页码手动调用setTotalCount函数
            Projection rowCountProjection = getRowCountProjection(condition);
            Long totalCount = null;
            if (rowCountProjection != null) {
                ProjectionList projectList = Projections.projectionList();
                projectList.add(rowCountProjection);
                criteria.setProjection(projectList);
                Object totalCountObj = criteria.uniqueResult();
                totalCount = totalCountObj == null ? 0L : (Long) totalCountObj;
                criteria.setProjection(null);
            }
            // 查询一页数据
            buildOrder(criteria, condition.getOrders());
            buildProjection(criteria, condition.getFunctions());
            criteria.setFirstResult(condition.getStart());
            criteria.setMaxResults(condition.getLimit());
            if (CollectionUtil.isEmpty(condition.getFunctions()))
                criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
            List<T> list = (List<T>) criteria.list();
            QueryResult<T> result = new QueryResult<T>();
            // 手动调用一下设置isSetTotalCountValue为false
            ObjectUtil.setField(result, "isSetTotalCountValue", false);
            result.addAll(list);
            if (totalCount != null) result.setTotalCount(totalCount.intValue());
            result.setStart(condition.getStart());
            result.setLimit(condition.getLimit());
            return result;
        }
    }

    // public static <S, M> QueryResult<M> queryList(Criteria criteria, QueryCondition condition, IAssemble<S, M> assemble) {
    // QueryResult<S> srcQueryResult = queryList(criteria, condition);
    // return srcQueryResult.assembler(assemble);
    // }

    private static Projection getRowCountProjection(QueryCondition condition) {
        for (FunctionInvoke function : condition.getFunctions()) {
            if (function.getType() == FunctionEnum.DISTINCT)
                return Projections.countDistinct(function.getPropertyName());
            // group by与分页连用取页码不对
            if (function.getType() == FunctionEnum.GROUPBY)
                return null;
        }
        return Projections.rowCount();
    }

    /**
     * 创建Projection
     * 
     * @param function
     * @return
     */
    private static Projection createProjection(FunctionInvoke function) {
        switch (function.getType()) {
            case SUM:
                return Projections.sum(function.getPropertyName());
            case COUNT:
                return Projections.count(function.getPropertyName());
            case DISTINCT:
                return Projections.distinct(Projections.property(function.getPropertyName()));
            case MAX:
                return Projections.max(function.getPropertyName());
            case MIN:
                return Projections.min(function.getPropertyName());
            case GROUPBY:
                return Projections.groupProperty(function.getPropertyName());
            case PROPERTY:
                return Projections.property(function.getPropertyName());
            case COUNTDISTINCT:
                return Projections.countDistinct(function.getPropertyName());
            default:
                return Projections.property(function.getPropertyName());
        }
    }

    /**
     * 组建Criteria
     * @param criteria
     * @param condition
     * @return 如果不需要执行sql提前返回则返回false
     */
    private static boolean buildCriteria(Criteria criteria, QueryCondition condition) {
        for (Criterion criterion : condition.getCriterions()) {
            if (checkCriterion(criterion)) criteria.add(criterion);
            else return false;
        }
        for (Map.Entry<String, TableAlias> entry : condition.getAliasMap().entrySet()) {
            criteria.createAlias(entry.getKey(), entry.getValue().getAlias(), entry.getValue().getType());
        }
        return true;
    }
    
    /**
     * 检查限制条件
     * @param criterion
     * @return
     */
    private static boolean checkCriterion(Criterion criterion) {
    	if (criterion instanceof InExpression) {
    		Object[] values = ObjectUtil.getFieldValue(criterion, "values");
    		if (ArrayUtil.isNil(values)) return false;
    	}
    	return true;
    }

    private static void buildOrder(Criteria criteria, List<PropertyOrder> orders) {
        for (PropertyOrder order : orders) {
            if (order.getOrderType() == OrderEnum.ASC) {
                criteria.addOrder(Order.asc(order.getPropertyName()));
            } else {
                criteria.addOrder(Order.desc(order.getPropertyName()));
            }
        }
    }

    private static void buildProjection(Criteria criteria, Set<FunctionInvoke> functions) {
        ProjectionList projectionList = Projections.projectionList();
        if (functions.size() > 0) {
            for (FunctionInvoke function : functions) {
                projectionList.add(createProjection(function));
            }
        }
        if (projectionList.getLength() > 0) {
            criteria.setProjection(projectionList);
        }
    }
}
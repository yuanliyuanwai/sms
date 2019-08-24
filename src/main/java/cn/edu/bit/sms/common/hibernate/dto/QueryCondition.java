package cn.edu.bit.sms.common.hibernate.dto;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.criterion.Criterion;
import org.hibernate.sql.JoinType;

/**
 * 查询条件
 * 
 * @author zhengchong.wan
 *
 */
public class QueryCondition {

    /**
     * 分页查询起始记录
     */
    private int start;

    /**
     * 分页查询多少条记录
     */
    private int limit;

    /**
     * hibernate的过滤条件
     */
    private List<Criterion> criterions = new ArrayList<Criterion>();

    /**
     * hibernate的排序
     */
    private List<PropertyOrder> orders = new ArrayList<PropertyOrder>();

    /**
     * 别名用于多表联合
     */
    private Map<String, TableAlias> aliasMap = new LinkedHashMap<String, TableAlias>();

    /**
     * 用于sql的函数查询：distinct, sum, count, min, max等等 具体支持的函数参照枚举对象ProjectionType
     */
    private Set<FunctionInvoke> functions = new LinkedHashSet<FunctionInvoke>();
    
    public int getStart() {
        return start;
    }

    public QueryCondition setStart(int start) {
        this.start = start;
        return this;
    }

    public int getLimit() {
        return limit;
    }

    public QueryCondition setLimit(int limit) {
        this.limit = limit;
        return this;
    }

    public QueryCondition addCriterion(Criterion criterion) {
        criterions.add(criterion);
        return this;
    }

    public QueryCondition addOrder(PropertyOrder order) {
        orders.add(order);
        return this;
    }

    public QueryCondition addAlias(String key, String value) {
        this.aliasMap.put(key, new TableAlias(value, JoinType.INNER_JOIN));
        return this;
    }

    public QueryCondition addAlias(String key, String value, JoinType joinType) {
        this.aliasMap.put(key, new TableAlias(value, joinType));
        return this;
    }

    public QueryCondition addFunction(FunctionInvoke function) {
        this.functions.add(function);
        return this;
    }

    public void clearFunctions() {
        this.functions.clear();
    }

    public List<Criterion> getCriterions() {
        return criterions;
    }

    public List<PropertyOrder> getOrders() {
        return orders;
    }

    public Map<String, TableAlias> getAliasMap() {
        return aliasMap;
    }

    public Set<FunctionInvoke> getFunctions() {
        return functions;
    }

}

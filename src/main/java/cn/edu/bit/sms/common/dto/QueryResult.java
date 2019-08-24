package cn.edu.bit.sms.common.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cn.edu.bit.sms.common.annotation.Comment;
import cn.edu.bit.sms.common.inter.IAssemble;
import cn.edu.bit.sms.common.util.CollectionUtil;

/**
 * 条件查询的结果
 * 
 * @author zhengchong.wan
 *
 * @param <S>
 */
public class QueryResult<S> {

    @Comment("总记录数")
    private int totalCount;

    @Comment("分页数据")
    private List<S> list = new ArrayList<S>();
    
    // 是否设置了totalCount的值
    private boolean isSetTotalCountValue = false;
    
    // mysql分页起始的记录索引
    private int start;
    
    // mysql分页取的记录条数
    private int limit;
    
    @Comment("当前页码")
    private Integer pageIndex;
    
    @Comment("总页码数")
    private Integer pageCount;

    /**
     * 空构造函数：
     * 使用分页需要调用需要调用:
     *     setTotalCount(), 
     *     addAll(),
     *     setStart(),
     *     setLimit()
     */
    public QueryResult() {
    	this(0, new ArrayList<S>());
    }

    /**
     * 构造函数
     * @param totalCount
     * @param list
     * 使用分页需要调用：
     *     setStart(),
     *     setLimit()
     */
    public QueryResult(int totalCount, List<S> list) {
        super();
        this.totalCount = totalCount;
        this.isSetTotalCountValue = true;
        if (!CollectionUtil.isEmpty(list)) this.list.addAll(list);
    }
    
    /**
     * 构造函数
     * @param totalCount
     * @param list
     * @param pageIndex
     * @param pageCount
     * 不需要调用什么了直接用吧
     */
    public QueryResult(int totalCount, List<S> list, int pageIndex, int pageCount) {
    	super();
        this.totalCount = totalCount;
        this.isSetTotalCountValue = true;
        if (!CollectionUtil.isEmpty(list)) this.list.addAll(list);
        this.pageIndex = pageIndex;
        this.pageCount = pageCount;
    }
    
    
    public int getTotalCount() {
    	if (!this.isSetTotalCountValue)
    		throw new RuntimeException("you must set a not null value of total count by yourself, because you maybe use the function of group by!");
        return totalCount;
    }
    
    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
        this.isSetTotalCountValue = true;
    }

    public List<S> getList() {
        return Collections.unmodifiableList(list);
    }

    public void setList(List<S> list) {
        this.list = list;
    }

    public void addAll(List<S> data) {
        this.list.addAll(data);
    }

	public void setStart(int start) {
		this.start = start;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getPageIndex() {
		// 直接赋值
		if (pageIndex != null) return pageIndex;
    	if (this.start >= 0 && this.limit > 0) {
    		return this.start / this.limit + 1;
    	} else {
    		return 0;
    	}
    }
    
    public int getPageCount() {
    	// 直接赋值
    	if (pageCount != null) return pageCount;
    	if (this.start >= 0 && this.limit > 0) {
			return getTotalCount() % limit == 0 ? (getTotalCount() / limit) : (getTotalCount() / limit + 1);
		} else {
			return 0;
		}
    }
    
    public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}

	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}

	/**
     * 装配出一个新的QueryResult
     * @param assemble
     * @return
     */
    public <T> QueryResult<T> assembler(IAssemble<S, T> assemble) {
    	List<T> targetList = new ArrayList<T>();
    	for (S s : this.getList()) {
    		targetList.add(assemble.assemble(s));
    	}
    	return new QueryResult<T>(this.totalCount, targetList, this.getPageIndex(), this.getPageCount());
    }

}

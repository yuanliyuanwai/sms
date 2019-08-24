package cn.edu.bit.sms.common.vo.req;

import cn.edu.bit.sms.common.annotation.Comment;

public class PageParam {
	
	@Comment("起始记录")
	private Integer start;
	
	@Comment("取多少行")
	private Integer limit;
	
	@Comment("页码")
	private Integer pageIndex;
	
	@Comment("每页多少条记录")
	private Integer pageSize;

	public Integer getStart() {
		if (start != null) return start;
		if (pageIndex != null && pageSize != null) {
			 if (this.pageIndex <= 0)  return 0;
		     return (this.pageIndex - 1) * pageSize;
		}
		return 0;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getLimit() {
		if (limit != null) return limit;
		if (pageIndex != null && pageSize != null) {
			 if (this.pageSize <= 0)  return 0;
		     return this.pageSize;
		}
		return 0;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageIndex() {
		return pageIndex;
	}

	public Integer getPageSize() {
		return pageSize;
	}

}

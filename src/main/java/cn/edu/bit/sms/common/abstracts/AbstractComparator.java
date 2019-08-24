package cn.edu.bit.sms.common.abstracts;

import java.util.Comparator;
import java.util.List;

public abstract class AbstractComparator<T> implements Comparator<T> {
	
	private List<T> list;

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}


}

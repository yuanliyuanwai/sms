package cn.edu.bit.sms.common.util;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import cn.edu.bit.sms.common.entity.BaseEntity;

/**
 * 实体的辅助类
 * @author zhenghcong.wan
 *
 */
public class EntityUtil {
	
	/**
	 * 转换成map
	 * @param collection
	 * @return
	 */
	public static <T extends BaseEntity> Map<Long, T> toMap(Collection<T> collection) {
		Map<Long, T> map = new HashMap<>();
		if (!CollectionUtil.isEmpty(collection)) {
			for (T t : collection) {
				map.put(t.getId(), t);
			}
		}
		return map;
	}

}

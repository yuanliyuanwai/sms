package cn.edu.bit.sms.common.util;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.edu.bit.sms.common.abstracts.AbstractTask;
import cn.edu.bit.sms.common.hibernate.constant.OrderEnum;
import cn.edu.bit.sms.common.inter.ICommand;

public class MapUtil {
	
	/**
	 * 对map进行按键排序
	 * @param map 待排序的map
	 * @param order 顺序
	 * @return
	 */
	public static <K extends Comparable<? super K>, V> Map<K, V> sortByKey(final Map<K, V> map, final OrderEnum order) {
		List<Map.Entry<K, V>> list = new LinkedList<Map.Entry<K, V>>(map.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<K, V>>() {
			public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
				if (order == OrderEnum.ASC) {
					return (o1.getKey()).compareTo(o2.getKey());
				} else {
					return -(o1.getKey()).compareTo(o2.getKey());
				}
			}
		});
		Map<K, V> result = new LinkedHashMap<K, V>();
		for (Map.Entry<K, V> entry : list) {
			result.put(entry.getKey(), entry.getValue());
		}
		return result;
	}

	/**
	 * 对map进行按值排序
	 * @param map 待排序的map
	 * @param order 顺序
	 * @return
	 */
	public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(final Map<K, V> map, final OrderEnum order) {
		List<Map.Entry<K, V>> list = new LinkedList<Map.Entry<K, V>>(map.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<K, V>>() {
			public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
				if (order == OrderEnum.ASC) {
					return (o1.getValue()).compareTo(o2.getValue());
				} else {
					return -(o1.getValue()).compareTo(o2.getValue());
				}
			}
		});
		Map<K, V> result = new LinkedHashMap<K, V>();
		for (Map.Entry<K, V> entry : list) {
			result.put(entry.getKey(), entry.getValue());
		}
		return result;
	}
	
	/**
	 * 对map进行排序
	 * @param map 待排序的map
	 * @param order 顺序
	 * @return
	 */
	public static <K, V> LinkedHashMap<K, V> sort(final Map<K, V> map, Comparator<Map.Entry<K, V>> comparator) {
		List<Map.Entry<K, V>> list = new LinkedList<Map.Entry<K, V>>(map.entrySet());
		Collections.sort(list, comparator);
		LinkedHashMap<K, V> result = new LinkedHashMap<K, V>();
		for (Map.Entry<K, V> entry : list) {
			result.put(entry.getKey(), entry.getValue());
		}
		return result;
	}
	
	/**
	 * 按照顺序递归
	 * @param map 需要递归的map
	 * @param comparator 比较起
	 * @param command 回调函数
	 */
	public static <K, V> void iteratorByOrder(Map<K, V> map, Comparator<Map.Entry<K, V>> comparator, ICommand<Map.Entry<K, V>> command) {
		// 还未遍历过的map
		Map<K, V> unIteratorMap = new LinkedHashMap<K, V>(map);
		while (unIteratorMap.size() > 0) {
			// 进行排序
			LinkedHashMap<K, V> sortMap = sort(unIteratorMap, comparator);
			Set<Map.Entry<K, V>> entrySet = sortMap.entrySet();
			// 遍历第一个
			for (Map.Entry<K, V> entry : entrySet) {
				command.execute(entry);
				unIteratorMap.remove(entry.getKey());
				break;
			}
		}
	}
	
	/**
	 * 按照顺序递归
	 * @param map 需要递归的map
	 * @param comparator 比较起
	 * @param command 回调函数
	 */
	public static <K, V> void iteratorByOrder(Map<K, V> map, Comparator<Map.Entry<K, V>> comparator, AbstractTask task) {
		// 还未遍历过的map
		Map<K, V> unIteratorMap = new LinkedHashMap<K, V>(map);
		while (unIteratorMap.size() > 0) {
			// 进行排序
			LinkedHashMap<K, V> sortMap = sort(unIteratorMap, comparator);
			Set<Map.Entry<K, V>> entrySet = sortMap.entrySet();
			// 遍历第一个
			for (Map.Entry<K, V> entry : entrySet) {
				task.addParameter("entry", entry).addParameter("leftMap", new LinkedHashMap<K, V>(unIteratorMap));
				task.run();
				unIteratorMap.remove(entry.getKey());
				break;
			}
		}
	}
	
	public static <K, V> boolean isEmpty(Map<K, V> map) {
		return map == null || map.isEmpty();
	}
	
	/**
	 * 对map进行错位排列
	 * @param map
	 * @param fromHead 为true时前面的往后面移动否则后面的往前面移动
	 * @return
	 */
	public static <K, V> LinkedHashMap<K, V> derange(LinkedHashMap<K, V> map, boolean fromHead) {
		LinkedHashMap<K, V> returnMap = new LinkedHashMap<K, V>();
		if (fromHead) {
			returnMap.putAll(map);
			K k = null;
			for (Map.Entry<K, V> entry : returnMap.entrySet()) {
				k =  entry.getKey();
				break;
			}
			if (k != null) {
				returnMap.put(k, returnMap.remove(k));
			}
		} else {
			int index = 0;
			LinkedHashMap<K, V> tempMap = new LinkedHashMap<K, V>();
			K k = null;
			for (Map.Entry<K, V> entry : map.entrySet()) {
				if (index == map.size() - 1) {
					k = entry.getKey();
				} else {
					tempMap.put(entry.getKey(), entry.getValue());
				}
				index++;
			}
			returnMap.put(k, map.get(k));
			returnMap.putAll(tempMap);
		}
		return returnMap;
	}
	
	public static void main(String[] args) {
		LinkedHashMap<Long, String> map = new LinkedHashMap<Long, String>();
		map.put(1L, "a");
		map.put(2L, "b");
		map.put(3L, "c");
		map.put(4L, "d");
		map.put(5L, "e");
		for (Map.Entry<Long, String> entry : derange(map, false).entrySet()) {
			System.out.println(entry.getKey() + "_" + entry.getValue());
		}
	}

}

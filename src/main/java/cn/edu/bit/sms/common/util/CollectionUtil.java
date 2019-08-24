package cn.edu.bit.sms.common.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import cn.edu.bit.sms.common.entity.BaseEntity;
import cn.edu.bit.sms.common.inter.IBeanFilter;
import cn.edu.bit.sms.common.inter.ICommand;

public class CollectionUtil {

    /**
     * 简单的随机遍历
     * 
     * @param collection
     */
    public static <T> void randomIterator(Collection<T> collection, ICommand<T> command) {
        // 先转换成数组
        List<T> list = new ArrayList<T>(collection);
        // 已经遍历过的索引
        Set<Integer> iteratorIndexSet = new HashSet<Integer>();
        Random random = new Random();
        while (true) {
            // 已经遍历结束了直接退出
            if (iteratorIndexSet.size() == list.size())
                break;
            int randomIndex = random.nextInt(list.size());
            // 已经遍历过了继续下一次循环
            if (iteratorIndexSet.contains(randomIndex))
                continue;
            iteratorIndexSet.add(randomIndex);
            T t = list.get(randomIndex);
            command.execute(t);
        }
    }

    public static <T> boolean isEmpty(Collection<T> collection) {
        return collection == null || collection.isEmpty();
    }

    /**
     * 对集合中的对象进行排列组合
     * 
     * @param collection
     * @param command
     */
    @SuppressWarnings("unchecked")
    public static <T> void permutate(Collection<T> collection, ICommand<List<T>> command) {
        permutate((T[]) collection.toArray(), 0, collection.size(), command, new byte[1]);
    }

    /**
     * 数组中的两个位置交换顺序
     * 
     * @param arr
     * @param i
     * @param j
     */
    private static <T> void swap(T[] arr, int i, int j) {
        T temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * 对数组进行排列组合
     * 
     * @param arr
     * @param start
     * @param length
     * @param command
     * @param flag
     *            方法返回的标志位
     */
    private static <T> void permutate(T[] arr, int start, int length, ICommand<List<T>> command, byte[] flag) {
        // 方法返回了
        if (flag[0] == 1)
            return;
        if (start == length - 1) {
            List<T> list = new ArrayList<T>();
            for (int i = 0; i < length; i++) {
                list.add(arr[i]);
            }
            boolean result = command.execute(list);
            if (!result)
                flag[0] = 1;
        } else {
            for (int i = start; i < length; i++) {
                swap(arr, start, i);
                permutate(arr, start + 1, length, command, flag);
                swap(arr, start, i);
            }
        }
    }

    /**
     * 对集合进行错位排列
     * 
     * @param collection
     */
    public static <T> void derange(Collection<T> collection) {
        if (isEmpty(collection))
            return;
        Iterator<T> iterator = collection.iterator();
        T firstValue = null;
        while (iterator.hasNext()) {
            firstValue = iterator.next();
            iterator.remove();
            break;
        }
        collection.add(firstValue);
    }

    /**
     * 返回实体的id列表
     * 
     * @param 实体对象列表
     * @return
     */
    public static <T extends BaseEntity> List<Long> getIds(Collection<T> collection) {
        List<Long> ids = new ArrayList<Long>();
        if (collection != null) {
            for (T entity : collection) {
                ids.add(entity.getId());
            }
        }
        return ids;
    }

    /**
     * 是否存在
     * 
     * @param items
     * @param id
     * @return
     */
    public static <T extends BaseEntity> T exist(List<T> items, Long id) {
        if (items == null || id == null) {
            return null;
        }
        for (T item : items) {
            if (item.getId().equals(id)) {
                return item;
            }
        }
        return null;
    }

    /**
     * 对集合进行过滤
     * @param collection
     * @param filter
     */
    public static void filter(Collection<?> collection, IBeanFilter filter) {
    	if (CollectionUtil.isEmpty(collection)) return;
    	Iterator<?> it = collection.iterator();
    	while (it.hasNext()) {
    		if (!filter.accept(it.next())) it.remove(); 
    	}
    }

}

package cn.edu.bit.sms.common.dto;

/**
 * 含有一个键一个值的键值对象
 * @author zhengchong.wan
 *
 * @param <K>
 * @param <V>
 */
public class Pair<K, V> {
	
	private K k;
	
	private V v;
	
	public Pair(K k) {
		this(k, null);
	}
	
	public Pair(K k, V v) {
		this.k = k;
		this.v = v;
	}

	public K getK() {
		return k;
	}

	public void setK(K k) {
		this.k = k;
	}

	public V getV() {
		return v;
	}

	public void setV(V v) {
		this.v = v;
	}
	
	

}

package cn.edu.bit.sms.common.dto;

/**
 * 返回任意的三个字段
 * @author zhengchong
 *
 * @param <K> 
 * @param <V>
 * @param <Z>
 */
public class ThreeCouple<K, V, Z> extends Pair<K, V> {
	
	private Z z;
	
	public ThreeCouple(K k) {
		super(k);
	}
	
	public ThreeCouple(K k, V v) {
		super(k, v);
	}

	public ThreeCouple(K k, V v, Z z) {
		super(k, v);
		this.z = z;
		// TODO Auto-generated constructor stub
	}

	public Z getZ() {
		return z;
	}

	public void setZ(Z z) {
		this.z = z;
	}
	
	

}

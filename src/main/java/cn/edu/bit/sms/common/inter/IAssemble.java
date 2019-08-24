package cn.edu.bit.sms.common.inter;

/**
 * 装配器
 * 将源对象装配成目标对象
 * @author zhengchong.wan
 *
 * @param <T>
 * @param <M>
 */
public interface IAssemble<S, T> {
	
	/**
	 * 对目标对象T进行装配和修改
	 * @param t
	 * @return
	 */
	T assemble(S s);

}

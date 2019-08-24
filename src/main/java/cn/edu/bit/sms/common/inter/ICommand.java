package cn.edu.bit.sms.common.inter;

public interface ICommand<T> {
	
	boolean execute(T t);

}

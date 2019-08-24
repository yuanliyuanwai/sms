package cn.edu.bit.sms.common.vo.res;

import cn.edu.bit.sms.common.exception.BusinessException;


/**
 * 返回的异常数据
 * 
 * @author yzhb
 * 
 */
public class ExceptionResult extends MessageResult<Object> {

     public ExceptionResult() {
         this.setCode("-1");
         this.setMessage("服务器问题");
     }

    public ExceptionResult(Exception ex) {
    	 this.setCode("-1");
        this.setMessage(ex.getMessage());
    }

    public ExceptionResult(BusinessException ex) {
    	 this.setCode("-1");
        this.setMessage(ex.getDescription());
    }

}

package cn.edu.bit.sms.common.action;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.edu.bit.sms.common.exception.BusinessException;
import cn.edu.bit.sms.common.util.Logger;
import cn.edu.bit.sms.common.vo.res.ExceptionResult;


public class BaseAction {

    /**
     * 统一的异常处理
     * 
     * @param ex 异常信息
     * @return json数据
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Object handleException(Exception ex) {
        String msg = ex.getMessage();
        ExceptionResult result = new ExceptionResult();
        dealException(result, ex, msg);
        return result;
    }

    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public Object handleException(BusinessException ex) {
        // 处理业务异常
        String msg = ((BusinessException) ex).getDescription();
        ExceptionResult result = new ExceptionResult((BusinessException) ex);
        Logger.error(msg, ex);
        return result;
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseBody
    public Object handleException(DataIntegrityViolationException ex) {
        // 违反外键约束时操作失败
        String msg = "数据关联导致操作失败";
        ExceptionResult result = new ExceptionResult(new BusinessException(msg));
        dealException(result, ex, msg);
        return result;
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public Object handleException(ConstraintViolationException ex) {
        // 违反外键约束时操作失败
        String msg = "数据关联导致操作失败";
        ExceptionResult result = new ExceptionResult(new BusinessException(msg));
        dealException(result, ex, msg);
        return result;
    }

    private void dealException(ExceptionResult result, Exception ex, String msg) {
        Logger.error(msg, ex);
        if (ex.getStackTrace().length > 0) {
            result.setData(ex.getStackTrace()[0]);
        }
    }

}

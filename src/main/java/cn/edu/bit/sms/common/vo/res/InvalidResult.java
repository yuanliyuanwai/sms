package cn.edu.bit.sms.common.vo.res;

import java.lang.reflect.Field;
import java.util.List;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import cn.edu.bit.sms.common.annotation.Comment;
import cn.edu.bit.sms.common.util.Logger;


public class InvalidResult<T> extends MessageResult<T> {

    public InvalidResult() {
    	this.setCode("-2");
        this.message = "参数格式有误";
    }

    public InvalidResult(BindingResult result) {
        this();
        List<ObjectError> list = result.getAllErrors();
        StringBuffer errorMsg = new StringBuffer();
        Object obj = result.getTarget();
        Class<?> clazz = obj.getClass();
        for (ObjectError error : list) {
            FieldError fieldError = (FieldError) error;
            String fieldName = getFieldDescription(clazz, fieldError.getField());
            errorMsg.append("," + fieldName + "：" + error.getDefaultMessage());
        }
        this.setMessage(errorMsg.toString().substring(1));
        Logger.error(this.getMessage());
    }

    /**
     * 获取类型字段的DescAnnotation注解
     * 
     * @param clazz
     * @param fieldName
     * @return
     */
    private String getFieldDescription(Class<?> clazz, String fieldName) {
        Field field = null;
        try {
            field = clazz.getDeclaredField(fieldName);
        } catch (Exception e) {
            field = null;
        }
        if (field != null) {
            Comment descAnno = field.getAnnotation(Comment.class);
            if (descAnno != null) {
                return descAnno.value();
            }
        } else {
            if (!clazz.getSuperclass().equals(Object.class)) {
                return getFieldDescription(clazz.getSuperclass(), fieldName);
            }
        }
        return fieldName;
    }

}

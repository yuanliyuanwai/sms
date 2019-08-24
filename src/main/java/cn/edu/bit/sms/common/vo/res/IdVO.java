package cn.edu.bit.sms.common.vo.res;

import cn.edu.bit.sms.common.annotation.Comment;

/**
 * 实体类的主键，用于输出参数
 * 
 * @author yzhb
 * 
 */
public class IdVO {

    @Comment(value = "对象ID")
    protected Long id;

    public IdVO() {

    }

    public IdVO(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}

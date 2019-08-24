package cn.edu.bit.sms.common.vo.req;

import javax.validation.constraints.NotNull;

import cn.edu.bit.sms.common.annotation.Comment;

public class IdParam {

    @Comment(value = "对象ID")
    @NotNull(message = "{msg.notnull}")
    private Long id;

    public IdParam() {

    }

    public IdParam(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}

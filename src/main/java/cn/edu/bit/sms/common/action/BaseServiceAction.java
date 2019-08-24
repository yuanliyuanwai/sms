package cn.edu.bit.sms.common.action;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.edu.bit.sms.common.annotation.Comment;
import cn.edu.bit.sms.common.entity.BaseEntity;
import cn.edu.bit.sms.common.exception.BusinessException;
import cn.edu.bit.sms.common.inter.AddValidate;
import cn.edu.bit.sms.common.inter.SetValidate;
import cn.edu.bit.sms.common.service.BaseService;
import cn.edu.bit.sms.common.vo.req.IdParam;
import cn.edu.bit.sms.common.vo.res.IdVO;
import cn.edu.bit.sms.common.vo.res.InvalidResult;
import cn.edu.bit.sms.common.vo.res.MessageResult;


/**
 * 基于服务的Controller基类，实现了常用的增删查改操作
 * 
 * @author yzhb
 * 
 * @param <S> 服务类
 * @param <T> 实体类
 */
public class BaseServiceAction<T extends BaseEntity, Service extends BaseService<T>> extends BaseAction {

    @Autowired
    protected Service service;

    @RequestMapping(value = "/add")
    @Comment(value = "新增")
    @ResponseBody
    public MessageResult<IdVO> add(HttpServletRequest req, HttpServletResponse res, @Validated({ AddValidate.class }) T t, BindingResult result) {
        if (result.hasErrors()) {
            return new InvalidResult<IdVO>(result);
        }
        return new MessageResult<IdVO>(new IdVO(service.save(t)));
    }

    @RequestMapping(value = "/set")
    @Comment(value = "修改")
    @ResponseBody
    public MessageResult<Void> set(HttpServletRequest req, HttpServletResponse res, @Validated({ SetValidate.class }) T t, BindingResult result) {
        if (result.hasErrors()) {
            return new InvalidResult<Void>(result);
        }
        service.update(t);
        return new MessageResult<Void>();
    }

    @RequestMapping(value = "/del")
    @Comment(value = "删除")
    @ResponseBody
    public MessageResult<Void> del(HttpServletRequest req, HttpServletResponse res, @Valid IdParam pk, BindingResult result) {
        if (result.hasErrors()) {
            return new InvalidResult<Void>(result);
        }
        try {
            service.delete(pk.getId());
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new BusinessException("数据已使用，不能删除！");
        }
        return new MessageResult<Void>();
    }

    @RequestMapping(value = "/get")
    @Comment(value = "获取")
    @ResponseBody
    public MessageResult<T> get(@Valid IdParam pk, BindingResult result) {
        if (result.hasErrors()) {
            return new InvalidResult<T>(result);
        }
        return new MessageResult<T>(service.get(pk.getId()));
    }

    @RequestMapping(value = "/findAll")
    @Comment(value = "查询所有")
    @ResponseBody
    public MessageResult<List<T>> findAll(HttpServletRequest req, HttpServletResponse res) {
        return new MessageResult<List<T>>(service.findAll());
    }


    public Class<T> getEntityClass() {
        @SuppressWarnings("unchecked")
        Class<T> entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
        return entityClass;
    }
}

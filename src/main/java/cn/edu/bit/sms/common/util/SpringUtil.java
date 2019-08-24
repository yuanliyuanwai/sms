package cn.edu.bit.sms.common.util;

import java.util.Collection;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * bean对象读写器
 * 
 * @author yzb
 * 
 */
public class SpringUtil implements ApplicationContextAware {
	
    private static ApplicationContext context;

    /**
     * 根据名称获取bean
     * 
     * @param name
     *            bean名称
     * @return
     */
    public static Object getBean(String name) {
        return context.getBean(name);
    }

    /**
     * 根据名称和类型获取bean
     * 
     * @param name
     * @param clazz
     * @return
     */
    public static <T> T getBean(String name, Class<T> clazz) {
        return context.getBean(name, clazz);
    }

    public static ApplicationContext getContext() {
        return context;
    }

    /**
     * 根据类型获取所有的bean
     * 
     * @param clazz
     *            bean类型
     * @return
     */
    public static <T> Collection<T> getBeansByType(Class<T> clazz) {
        return context.getBeansOfType(clazz).values();
    }

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        SpringUtil.context = context;
    }

}

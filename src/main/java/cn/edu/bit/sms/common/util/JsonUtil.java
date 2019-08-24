package cn.edu.bit.sms.common.util;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * Json处理帮助类
 */
public class JsonUtil {

    /**
     * 对象转换成JSON字符串
     * 
     * @param obj 需要转换的对象
     * @return 对象的string字符
     */
    public static String toJson(Object obj) {
        return JSON.toJSONString(obj, SerializerFeature.WriteDateUseDateFormat);
    }

    /**
     * JSON字符串转换成对象
     * 
     * @param json 需要转换的字符串
     * @return 对象
     */
    public static Object parse(String json) {
        return JSON.parse(json);
    }
    
    /**
     * 将对象转换成map
     * @param obj
     * @return
     */
    public static Map<String, Object> objectToMap(Object obj) {
    	String json = toJson(obj);
    	@SuppressWarnings("unchecked")
		Map<String, Object> map = parseJson(json, Map.class);
    	return map;
    }

    /**
     * JSON字符串转换成对象
     * 
     * @param json 需要转换的字符串
     * @param clazz 需要转换的对象类型
     * @return 对象
     */
    public static <T> T parseJson(String json, Class<T> clazz) {
        return JSON.parseObject(json, clazz);
    }
    
    /**
     * JSON字符串转换成对象列表
     * 
     * @param jsonString 需要转换的字符串
     * @param clazz 需要转换的对象类型
     * @return 对象列表
     */
    public static <T> List<T> parseJsonList(String json, Class<T> clazz) {
        return JSON.parseArray(json, clazz);
    }

}
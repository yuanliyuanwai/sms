package cn.edu.bit.sms.common.http;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

import cn.edu.bit.sms.common.util.ObjectUtil;

/**
 * 自定义json输出字符串null为空
 * 
 * @author yzb
 *
 */
public class ObjectMappingCustomer extends ObjectMapper {

    private static final long serialVersionUID = -3627726731634905374L;

    public ObjectMappingCustomer() {
        super();
        FilterProvider filters = new SimpleFilterProvider().addFilter("idNamePropertyFilter", SimpleBeanPropertyFilter.filterOutAllExcept("id", "name"))
        		.addFilter("idNameCodePropertyFilter", SimpleBeanPropertyFilter.filterOutAllExcept("id", "name", "code"));
        this.setFilters(filters);
        this.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>() {

            @Override
            public void serialize(Object arg0, JsonGenerator arg1, SerializerProvider arg2) throws IOException, JsonProcessingException {

                String fieldName = arg1.getOutputContext().getCurrentName();
                Object bean = arg1.getCurrentValue();
                // 找属性不行只能找方法
                String firstLetter = fieldName.substring(0, 1);
                String methodName = "get" + fieldName.replaceFirst(firstLetter, firstLetter.toUpperCase());
                Class<?> returnType = ObjectUtil.getReturnType(bean.getClass(), methodName);
                if (returnType != null && returnType == String.class) {
                    arg1.writeString("");
                    return;
                }
                arg1.writeObject(null);
            }
        });

    }

}

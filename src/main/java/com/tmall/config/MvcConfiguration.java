package com.tmall.config;

import cn.hutool.core.util.ReflectUtil;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

/**
 * @Author : DongWJ
 * @Date : 2022/4/3 21:33
 */

//@Configuration
@Slf4j
public class MvcConfiguration  implements WebMvcConfigurer {

        @Override
         public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
            converters.stream().forEach(c -> {
                if (c instanceof MappingJackson2HttpMessageConverter) {
                    MappingJackson2HttpMessageConverter converter = (MappingJackson2HttpMessageConverter) c;
                    SerializerProvider serializerProvider = converter.getObjectMapper().getSerializerProvider();
                    serializerProvider.setNullValueSerializer(new NullValueJsonSerializer());
                }
            });
        }      private class NullValueJsonSerializer extends JsonSerializer<Object> {
            @Override
            public void serialize(Object value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
                String fieldName = gen.getOutputContext().getCurrentName();
                try {
                    Class<?> aClass = gen.getCurrentValue().getClass();
                    // 获取所有的属性字段，包含其父类的
                     Map<String, Field> fieldMap = ReflectUtil.getFieldMap(aClass);
                     Field field = fieldMap.get(fieldName);
                     if (null == field) {
                     throw new NoSuchFieldException(String.format(" no such #%s# field", fieldName));
                     }                 Class<?> fieldType = field.getType();
                     //空字符串
                    if (fieldType == String.class) {
                     gen.writeString("");
                     } else if (fieldType == List.class) {
                     gen.writeStartArray();                     gen.writeEndArray();
                     } else if (fieldType.isArray()) { // 空数组
                     gen.writeStartArray();                     gen.writeEndArray();
                     } else if (fieldType == Map.class) { // 空map
                     gen.writeStartObject();                     gen.writeEndObject();                 }
                     else {                     gen.writeNull();
                     }
                     } catch (NoSuchFieldException e) {
                     e.printStackTrace();                 log.error(e.getLocalizedMessage());
                }
            }
        }
    }



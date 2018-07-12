package com.dengjk.cloudmoviehystrix.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Dengjk
 * @create 2018-04-28 22:23
 * @desc
 **/
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {
    /**
     * 跟换jackson  为fastjson
     *
     * @param converters
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.configureMessageConverters(converters);
        FastJsonHttpMessageConverter oFastConverter = new FastJsonHttpMessageConverter();
        FastJsonConfig oFastJsonConfig = new FastJsonConfig();
        oFastJsonConfig.setDateFormat("yyyy-MM-dd HH:mm:ss");
        oFastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat, SerializerFeature.WriteNullListAsEmpty,SerializerFeature.WriteMapNullValue);
        oFastConverter.setFastJsonConfig(oFastJsonConfig);
        List<MediaType> oFastMediaTypeList = new ArrayList<>();
        oFastMediaTypeList.add(MediaType.APPLICATION_JSON_UTF8);
        oFastConverter.setSupportedMediaTypes(oFastMediaTypeList);
        converters.add(oFastConverter);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}

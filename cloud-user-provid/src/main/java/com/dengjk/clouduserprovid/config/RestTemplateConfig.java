package com.dengjk.clouduserprovid.config;

import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import java.nio.charset.Charset;

/**
 * @author Dengjk
 * @create 2018-04-28 13:58
 * @desc  http请求resttemplate配置
 **/
@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate() {
        RequestConfig config = RequestConfig.custom().setConnectionRequestTimeout(10000).setConnectTimeout(10000).setSocketTimeout(10000).build();
        HttpClientBuilder builder = HttpClientBuilder.create().setDefaultRequestConfig(config).setRetryHandler(new DefaultHttpRequestRetryHandler(5, false));
        HttpClient httpClient = builder.build();
        HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
        httpRequestFactory.setReadTimeout(30*1000);
        return new RestTemplate(httpRequestFactory);
    }

    @Bean(value = "restTemplateForString")
    public RestTemplate restTemplateForString(){
        StringHttpMessageConverter m = new StringHttpMessageConverter(Charset.forName("UTF-8"));
        return new RestTemplateBuilder()
                .setConnectTimeout(60000)
                .setReadTimeout(60000)
                .additionalMessageConverters(m)
                .build();
    }

    @Bean(name = "restTemplateForReRequest")
    public RestTemplate restTemplateForReRequest(){
        StringHttpMessageConverter m = new StringHttpMessageConverter(Charset.forName("UTF-8"));
        //生成一个设置了连接超时时间、请求超时时间、异常最大重试次数的httpClient
        RequestConfig config = RequestConfig.custom().setSocketTimeout(30000).build();
        HttpClientBuilder builder = HttpClientBuilder.create().setDefaultRequestConfig(config).setRetryHandler(new DefaultHttpRequestRetryHandler(5, false));
        HttpClient httpClient = builder.build();
        //使用httpClient创建一个ClientHttpRequestFactory的实现
        HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
        httpRequestFactory.setConnectionRequestTimeout(30000);
        httpRequestFactory.setConnectTimeout(30000);
        httpRequestFactory.setReadTimeout(600000);
        RestTemplate restTemplate=new RestTemplate(httpRequestFactory);
        restTemplate.getMessageConverters().add(m);
        return restTemplate;
    }
}

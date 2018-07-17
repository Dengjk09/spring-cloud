package com.dengjk.eurekadiscovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @author Dengjk
 * @create 2018-06-29 16:02
 * @desc  spring-cloud   api统一网关
 *
 *	把所有的微服务给代理起来,提供一个入口 类似于nginx的的反向代理
 *  所有使用了zuul微服务,默认都会用hystrix进行包裹
 **/
@SpringBootApplication
@EnableZuulProxy
public class ApiGetwayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGetwayApplication.class, args);
	}
}

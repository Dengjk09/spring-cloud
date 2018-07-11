package com.dengjk.cloudmovieconsumer;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class CloudMovieConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudMovieConsumerApplication.class, args);
	}

	@Bean
	public RequestInterceptor headerInterceptor() {
		return new RequestInterceptor() {
			@Override
			public void apply(RequestTemplate requestTemplate) {
				requestTemplate.header("Cookie", "703EDE2DCE92A16E05669D61055C7190");
			}
		};
	}
}

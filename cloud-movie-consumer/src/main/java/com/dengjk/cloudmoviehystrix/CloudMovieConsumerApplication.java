package com.dengjk.cloudmoviehystrix;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

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

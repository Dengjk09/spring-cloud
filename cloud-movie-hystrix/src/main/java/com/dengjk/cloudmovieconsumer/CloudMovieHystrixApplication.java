package com.dengjk.cloudmovieconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
/**开启hystrix*/
@EnableCircuitBreaker
public class CloudMovieHystrixApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudMovieHystrixApplication.class, args);
	}
}

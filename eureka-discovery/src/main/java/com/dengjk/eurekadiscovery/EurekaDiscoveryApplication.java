package com.dengjk.eurekadiscovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author Dengjk
 * @create 2018-06-29 16:02
 * @desc 本服务为eureka的服务发现和注册
 *
 * 	加上EnableEurekaServer 注解  申明为一个Eureka服务,
 *
 **/
@SpringBootApplication
@EnableEurekaServer
public class EurekaDiscoveryApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaDiscoveryApplication.class, args);
	}
}

package com.dengjk.cloudconfigservier;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @author Dengjk
 * @create 2018-06-29 16:02
 * @desc spring-cloud-config-service  服务
 *
 **/
@SpringBootApplication
@EnableConfigServer
public class CloudConfigServierApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudConfigServierApplication.class, args);
	}
}

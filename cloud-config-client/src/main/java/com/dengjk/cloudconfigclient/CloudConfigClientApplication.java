package com.dengjk.cloudconfigclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author Dengjk
 * @create 2018-06-29 16:02
 * @desc spring-cloud-config-client  客户端
 * 		只要导入依赖就ok了
 * 	注意: springboot在启动时候会报错 ,找不到git上面的属性配置文件profile这个值  仔细看日志 会发现config-client连接的是默认的localhost:8888 ,说明我们配置的url没生效
 *		原因是： config-client在已启动时候必须要先加载 config-server的—>config-server再去初始化git上面的值
 *			springboot 启动时候是会加载两个默认属性配置文件 application 和 bootstrap的  文件文件的加载顺序 bootstrap要高于application的
 *			如果把config-client的连接信息配置在application上面,就会有报错,  必须写在bootstrap文件中
 *
 * @RefreshScope 该注解表明这里是一个可自动刷新 重新加载配置文件
 * 站点来访问  http://localhost:9097/refresh  重新加载git上面的配置文件
 *
 *  也可以通过springcloud-bus 来刷新  ,嵌入rabbitmq 做为消息推送,更新所有的config-server下面的所有client   也可以指定刷新某一个节点/ bus / refresh？destination = customers：9000
 *
 *  导入bus-amqp的依赖
 *  配置mq的连接信息
 *  http://localhost:9097/bus/refresh
 *
 *  可以实现自动刷新 ,利用git上面的功能,如果有push的  ,就可以自动请求地址： http://localhost:9097/bus/refresh 来实现自动刷新
 *
 *  rabbitmq信息  config-client连接上了mq之后  会自动建立一个topic类型的交换机:springCloudBus   并且绑定一个队列
 *
 **/
@SpringBootApplication
@EnableEurekaClient
public class CloudConfigClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudConfigClientApplication.class, args);
	}
}

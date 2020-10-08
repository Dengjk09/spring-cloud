package com.dengjk.springadmin;

import de.codecentric.boot.admin.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * springboot-admin  2.0一下需要依赖ui的包
 */


@SpringBootApplication
@EnableAdminServer
public class SpringAdminApplication {

    /**
     * 12
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(SpringAdminApplication.class, args);
    }

}

package com.dengjk.cloudconfigclient.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Dengjk
 * @create 2018-06-29 16:02
 * @desc 编写一个controller来模拟读取config-server上在git上面的配置文件信息
 * @RefreshScope 该注解表明这里是一个可自动刷新 重新加载配置文件
 * 站点来访问  http://localhost:9097/refresh  重新加载git上面的配置文件
 */
@RestController
@RefreshScope
public class ReadProfileInfoController {

    @Value("${profile}")
    private String profile;

    @GetMapping("/getProfileInfo")
    public String getProfileInfo() {
        return profile;
    }
}

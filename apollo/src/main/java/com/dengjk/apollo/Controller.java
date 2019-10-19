package com.dengjk.apollo;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Dengjk
 * @create 2019-10-18 23:11
 * @desc 注意: 要连接apollo 服务端  liunx和win必须要配置环境
 * <p>
 * 新建文件:
 * 对于Mac/Linux，文件位置为/opt/settings/server.properties
 * 对于Windows，文件位置为C:\opt\settings\server.properties
 * 文件内容:  env=DEV
 **/
@RestController
public class Controller {

    /**
     * apollo所有的配置环境
     */
    @ApolloConfig
    private Config config;

    @Value("${app_name}")
    private String appName;

    @Value("${dev.name}")
    private String devName;

    @GetMapping("readProp")
    public String readProp() {
        return appName + devName;
    }
}

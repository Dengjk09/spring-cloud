package com.dengjk.springadmin.controller;

import com.google.common.collect.Maps;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.util.Map;

/**
 * @author Dengjk
 * @create 2019-12-11 9:31
 * @desc 测试获取k8s的地址和ip
 **/
@RequestMapping("/admin")
@RestController
public class AdminController {


    @GetMapping("/hostInfo")
    public Map<String, Object> hostInfo(HttpServletRequest request) throws Exception {
        Map<String, Object> resultMap = Maps.newConcurrentMap();
        /**获取本机ip*/
        InetAddress addr = InetAddress.getLocalHost();
        String ip = addr.getHostAddress().toString();
        /**获取本机计算机名称*/
        String hostName = addr.getHostName().toString();
        resultMap.put("localIp", ip);
        resultMap.put("hostName", hostName);
        resultMap.put("requestIp", request.getRemoteAddr());
        resultMap.put("serverPort", request.getServerPort());
        resultMap.put("requestPath", request.getRequestURI());
        resultMap.put("requestUrl", request.getRequestURL().toString());
        return resultMap;
    }
}

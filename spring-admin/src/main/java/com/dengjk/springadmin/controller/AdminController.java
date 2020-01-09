package com.dengjk.springadmin.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.util.Enumeration;
import java.util.Map;

/**
 * @author Dengjk
 * @create 2019-12-11 9:31
 * @desc 测试获取k8s的地址和ip
 **/
@RequestMapping("/admin")
@RestController
@Slf4j
public class AdminController {


    @GetMapping("/hostInfo")
    public Map<String, Object> hostInfo(HttpServletRequest request) throws Exception {
        Map<String, Object> resultMap = Maps.newConcurrentMap();
        /**获取本机ip*/
        InetAddress addr = InetAddress.getLocalHost();
        String ip = addr.getHostAddress().toString();
        /**获取本机计算机名称*/
        String hostName = addr.getHostName().toString();
        String serverName = request.getServerName();
        String remoteHost = request.getRemoteHost();
        String localAddr = request.getLocalAddr();
        int remotePort = request.getRemotePort();
        resultMap.put("version", "v4");
        resultMap.put("localIp", ip);
        resultMap.put("hostName", hostName);
        resultMap.put("requestIp", request.getRemoteAddr());
        resultMap.put("serverPort", request.getServerPort());
        resultMap.put("requestPath", request.getRequestURI());
        resultMap.put("requestUrl", request.getRequestURL().toString());
        resultMap.put("serverName", serverName);
        resultMap.put("remoteHost", remoteHost);
        resultMap.put("localAddr", localAddr);
        resultMap.put("remotePort", remotePort);
        /**代理信息*/
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            resultMap.put(key, value);
        }
        log.info("请求信息:{}", JSON.toJSONString(resultMap));
        return resultMap;
    }
}

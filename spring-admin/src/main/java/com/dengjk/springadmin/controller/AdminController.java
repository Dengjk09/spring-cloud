package com.dengjk.springadmin.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.extra.servlet.ServletUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.UserAgent;
import lombok.extern.slf4j.Slf4j;
import org.lionsoul.ip2region.DataBlock;
import org.lionsoul.ip2region.DbConfig;
import org.lionsoul.ip2region.DbSearcher;
import org.lionsoul.ip2region.Util;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
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
        resultMap.put("version", "v5");
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
        resultMap.put("browserName", getBrowser(request));
        resultMap.put("realIp", this.getRealIp(request));
        resultMap.put("ipUserInfo", this.analyzeIp(this.getRealIp(request), request));
        /**使用hutool工具获取ip，该工具解析了一般的代理ip*/
        String clientIP = ServletUtil.getClientIP(request);
        /**获取所有的头信息*/
        Map<String, String> headerMap = ServletUtil.getHeaderMap(request);
        resultMap.putAll(headerMap);
        log.info("请求信息:{}", JSON.toJSONString(resultMap));
        return resultMap;
    }


    /**
     * 解析ip
     *
     * @param ip
     * @return
     * @throws Exception
     */
    @GetMapping("/analyzeIp")
    public JSONObject analyzeIp(String ip, HttpServletRequest request) throws Exception {
        String header1 = request.getHeader("x-Original-Forwarded-For");
        String header = request.getHeader("x-forwarded-for");
        String address = this.getAddress(ip);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("ipInfo", address);
        jsonObject.put("originIp", ip);
        jsonObject.put("x-forwarded-for", header);
        jsonObject.put("x-Original-Forwarded-For", header1);
        return jsonObject;
    }


    /**
     * 测试 ingress-url过长
     *
     * @param orderIds
     * @return
     */
    @GetMapping("/testGetUrl")
    public List<String> testGetUrl(@RequestParam(required = true) List<String> orderIds) {
        return orderIds;
    }


    public String getAddress(String ip) throws Exception {
        /**判断是否是真实地址*/
        if (!Util.isIpAddress(ip)) {
            return "";
        }
        String filePath = "/opt/spring-admin/ip2region.db";
        boolean exist = FileUtil.exist(filePath);
        if (!exist) {
            /**如果文件不存在 读取文件*/
            InputStream in = new ClassPathResource("ip2region/ip2region.db").getInputStream();
            FileUtil.writeFromStream(in, filePath);
        }
        DbSearcher searcher = new DbSearcher(new DbConfig(), filePath);
        DataBlock dataBlock = searcher.btreeSearch(ip);
        searcher.close();
        return dataBlock.getRegion().replaceAll("\\|0|0\\|", "");
    }


    /**
     * 获取ip地址
     * <p>
     * 获取用户真实IP地址，不使用request.getRemoteAddr();的原因是有可能用户使用了代理软件方式避免真实IP地址。
     * * 可是，如果通过了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP值，究竟哪个才是真正的用户端的真实IP呢？
     * * 答案是取X-Forwarded-For中第一个非unknown的有效IP字符串
     */
    public String getRealIp(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            /**针对k8s*/
            log.info("x-Original-Forwarded-For:{}", request.getHeader("x-Original-Forwarded-For"));
            ip = request.getHeader("x-Original-Forwarded-For");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            log.info("直接获取了远程地址:{}", request.getRemoteAddr());
            ip = request.getRemoteAddr();
        }
        /**多个ip,取第一个*/
        if (ip.contains(",")) {
            ip = ip.split(",")[0];
        }
        /**如果是本机,id取 本机的地址*/
        if ("127.0.0.1".equals(ip)) {
            try {
                ip = InetAddress.getLocalHost().getHostAddress();
            } catch (UnknownHostException e) {
            }
        }
        return ip;
    }


    public String getBrowser(HttpServletRequest request) {
        UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
        Browser browser = userAgent.getBrowser();
        return browser.getName();
    }
}

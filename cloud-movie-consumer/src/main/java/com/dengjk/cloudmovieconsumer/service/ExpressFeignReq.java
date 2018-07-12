package com.dengjk.cloudmovieconsumer.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.cloud.netflix.feign.FeignClientProperties;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Dengjk
 * @create 2018-07-11 10:58
 * @desc
 **/
@FeignClient(name = "express",url = "localhost:8086",configuration = FeignClientProperties.FeignClientConfiguration.class)
public interface ExpressFeignReq {

    @GetMapping("/")
    String getExpress();
}

package com.dengjk.cloudmoviehystrix.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.cloud.netflix.feign.FeignClientProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Dengjk
 * @create 2018-07-11 10:58
 * @desc
 **/
@FeignClient(name = "cloud-user-provid",configuration = FeignClientProperties.FeignClientConfiguration.class)
public interface ExpressFeignReq {

    @GetMapping("/user/getUser/{id}")
    String getExpress(@PathVariable(value = "id")Integer id );
}

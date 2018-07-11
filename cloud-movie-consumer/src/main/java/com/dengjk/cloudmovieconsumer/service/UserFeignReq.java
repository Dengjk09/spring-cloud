package com.dengjk.cloudmovieconsumer.service;

import com.dengjk.cloudmovieconsumer.entity.UserEntity;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.cloud.netflix.feign.FeignClientProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Dengjk
 * @create 2018-07-11 8:13
 * @desc
 *
 *  使用feig
 *    1.导入feign的依赖,在eurenka的netflix虽然有feign的包 但是不全,启动会报错
 *    2.再启动类上加上EnableFeignClients 开启feign
 *    3.编写feign接口
 *
 **/
@FeignClient(name = "cloud-user-provid")
public interface UserFeignReq {

    /***
     * 使用feignd调用用户接口
     * @param
     * @return
     */
    @GetMapping(value = "/user/getUser/{id}")
    UserEntity getUserById(@PathVariable(value = "id") Integer id );

}

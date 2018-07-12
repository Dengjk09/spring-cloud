package com.dengjk.cloudmoviehystrix.controller;

import com.dengjk.cloudmoviehystrix.entity.UserEntity;
import com.dengjk.cloudmoviehystrix.service.ExpressFeignReq;
import com.dengjk.cloudmoviehystrix.service.UserFeignReq;
import com.dengjk.cloudmoviehystrix.service.impl.UserFeignReqImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/movie")
@Api(value = "MovieController", description = "电影相关RestFul接口")
public class MovieController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UserFeignReq userFeignReq;

    @Autowired
    private ExpressFeignReq expressFeignReq;

    @Autowired
    private UserFeignReqImpl userFeignReqImpl;

    @GetMapping("getUser/{id}")
    @ApiOperation("使用ribbon负载调用获取用户信息接口")
    public UserEntity getUserById(@PathVariable(value = "id") Integer id) {
        UserEntity userEntity = restTemplate.getForObject("http://cloud-user-provid/user/getUser/" + id, UserEntity.class);
        return userEntity;
    }

    @GetMapping("getUserById2Hystrix/{id}")
    @ApiOperation("测试Hystrix进入熔断模式")
    public UserEntity getUserById2Hystrix(@PathVariable(value = "id") Integer id) {
        return userFeignReqImpl.getUserByHystrix(id);
    }
}

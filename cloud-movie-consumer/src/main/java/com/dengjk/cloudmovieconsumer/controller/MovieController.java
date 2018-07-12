package com.dengjk.cloudmovieconsumer.controller;

import com.dengjk.cloudmovieconsumer.entity.UserEntity;
import com.dengjk.cloudmovieconsumer.service.ExpressFeignReq;
import com.dengjk.cloudmovieconsumer.service.UserFeignReq;
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

    @GetMapping("getUser/{id}")
    @ApiOperation("使用ribbon负载调用获取用户信息接口")
    public UserEntity getUserById(@PathVariable(value = "id") Integer id) {
        UserEntity userEntity = restTemplate.getForObject("http://cloud-user-provid/user/getUser/" + id, UserEntity.class);
        return userEntity;
    }

    @GetMapping("/getUserByFeign/{id}")
    @ApiOperation("使用申明式feign获取用户信息")
    public UserEntity getUserByIdUFeign(@PathVariable(value = "id") Integer id){
       return userFeignReq.getUserById(id);
    }

    @GetMapping("/getExpressInfo")
    public String getExpressInfo(){
        return expressFeignReq.getExpress();
    }
}

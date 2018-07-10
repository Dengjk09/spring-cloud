package com.dengjk.cloudmovieconsumer.controller;

import com.dengjk.cloudmovieconsumer.entity.UserEntity;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
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

    @GetMapping("getUser/{id}")
    public UserEntity getUserById(@PathVariable(value = "id") Integer id) {
        UserEntity userEntity = restTemplate.getForObject("http://cloud-user-provid/user/getUser/" + id, UserEntity.class);
        return userEntity;
    }
}

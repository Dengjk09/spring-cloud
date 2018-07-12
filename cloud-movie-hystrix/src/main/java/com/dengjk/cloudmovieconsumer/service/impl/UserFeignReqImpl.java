package com.dengjk.cloudmovieconsumer.service.impl;

import com.dengjk.cloudmovieconsumer.entity.UserEntity;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author Dengjk
 * @create 2018-07-12 9:36
 * @desc  加入hystrix   请求接口  如果调用失败,返回getUserByHystrixRallback的内容
 *
 *         注意  错误返回的方法,参数要和请求的方法参数一样
 *
 **/
@Service
public class UserFeignReqImpl {

    @Autowired
    private    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "getUserByHystrixRallback",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")})
    public UserEntity getUserByHystrix(Integer id ){
       return new RestTemplate().getForObject("http://cloud-user-provid/user/getUser/" + id, UserEntity.class);
    }


    public UserEntity getUserByHystrixRallback(Integer id){
        UserEntity userEntity = new UserEntity();
        userEntity.setId(88);
        userEntity.setMsg("进入了fallBack方法");
        return userEntity;
    }

}

package com.dengjk.cloudmoviehystrix.service.impl;

import com.dengjk.cloudmoviehystrix.entity.UserEntity;
import com.dengjk.cloudmoviehystrix.service.UserFeignReq;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
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
public class UserFeignHystrixFallBack implements UserFeignReq {
    @Override
    public UserEntity getUserById(Integer id) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(888);
        userEntity.setMsg("进入熔断中,这是默认返回值");
        return userEntity;
    }
}

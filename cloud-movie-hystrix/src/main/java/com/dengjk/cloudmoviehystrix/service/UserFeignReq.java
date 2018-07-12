package com.dengjk.cloudmoviehystrix.service;

import com.dengjk.cloudmoviehystrix.entity.UserEntity;
import com.dengjk.cloudmoviehystrix.service.impl.UserFeignHystrixFallBack;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.cloud.netflix.feign.FeignClientProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
 *    注意： feign的官方文档中说feign是默认开启hystrix的,实际并没有  需要再配置文件中feign.hystrix.enabled=true 开了  调用接口失败就会fallback的实现类中
 **/
@FeignClient(value = "cloud-user-provid",fallback = UserFeignHystrixFallBack.class )
public interface UserFeignReq {

    /***
     * 使用feignd调用用户接口  当接口发生异常时候会进入UserFeignHystrixFallBack
     * @param
     * @return
     */
    @RequestMapping (value = "/user/getUser/{id}",method = RequestMethod.GET)
    UserEntity getUserById(@PathVariable(value = "id") Integer id );

}

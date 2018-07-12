package com.dengjk.cloudmoviehystrix.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Dengjk
 * @create 2018-07-10 8:33
 * @desc  两种方法指定ribbon负载算法
 *         1.通过配置类写一个bean返回对应的算法实例
 *         2.通过配置文件来指定某个服务使用某种算法    server服务名字.ribbon.NFLoadBalancerRuleClassName=com.netflix.loadbalancer.RandomRule
 *
 **/
/**加上RibbonClient注解标志可以指定某个服务,指定用默认负载算法
 * 如果想要为不同服务指定不同的负载算法 ,就只能是在CompanScan()扫描之外配置
 * */
@Configuration
@RibbonClient(name = "cloud-user-provid",configuration = LoadBalanceIRule.class)
public class LoadBalanceIRule {
    /***
     * 自定义负载均衡的算法
     * @return
     *   Ribbon默认负载算法是轮询 RoundRobinRule()
     *   RandomRule() 随机算法
     *   WeightedResponseTimeRule() 根据响应时间来分配weight,响应时间越长分配weight越低,被命中的几率越低
     *   BestAvailableRule() 选择一个并发最小服务,逐个考察Server，如果Server被tripped了，则忽略，在选择其中ActiveRequestsCount最小的server
     *   AvailabilityFilteringRule() 过滤掉那些因为一直连接失败的被标记为circuit tripped的后端server，并过滤掉那些高并发的的后端server（active connections 超过配置的阈值）
     */

    @Bean
    public IRule MyIRule(){
        return  new RandomRule();
    }
}

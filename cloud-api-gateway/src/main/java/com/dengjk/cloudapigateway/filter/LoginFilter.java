package com.dengjk.cloudapigateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Dengjk
 * @create 2019-05-31 22:05
 * @desc 网关登入 身份信息验证
 **/
@Component
public class LoginFilter extends ZuulFilter {


    /**
     * 过滤的类型,在那个节点上拦截
     * pre：可以在请求被路由之前调用。
     * routing：在路由请求时候被调用。
     * post：在routing和error过滤器之后被调用。
     * error：处理请求时发生错误时被调用。
     *
     * @return
     */
    @Override
    public String filterType() {
        //return "pre";
        return FilterConstants.PRE_TYPE;
    }


    /**
     * 通过int值来定义过滤器的执行顺序，数值越小优先级越高。
     *
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }


    /**
     * 返回一个boolean类型来判断该过滤器是否要执行。我们可以通过此方法来指定过滤器的有效范围。
     *
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }


    /**
     * 真正执行判断逻辑
     *
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        HttpServletResponse response = currentContext.getResponse();
        String authorization = request.getHeader("Authorization");
        if (StringUtils.isEmpty(authorization)) {
            /**拒绝访问,不在往下执行*/
            currentContext.setSendZuulResponse(false);
            /**设置响应码*/
            currentContext.setResponseStatusCode(401);
            currentContext.setResponseBody("{'msg':'认证失败'}");
            response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            return null;
        }
        return null;
    }
}

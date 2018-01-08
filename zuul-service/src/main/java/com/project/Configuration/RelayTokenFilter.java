package com.project.Configuration;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;


@Component
public class RelayTokenFilter extends ZuulFilter {
    private static final Logger LOG = LoggerFactory.getLogger(RelayTokenFilter.class);
    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        ctx.addZuulRequestHeader("Authorization",request.getHeader("Authorization"));
        LOG.info("Headers : {}", "Authorization" + "=" + request.getHeader("Authorization"));
        System.out.println(request.getHeader("Authorization").toString());
        return null;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 10000;
    }
}
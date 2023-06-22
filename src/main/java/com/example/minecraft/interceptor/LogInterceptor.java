package com.example.minecraft.interceptor;

import com.example.minecraft.prometheus.MetricChanger;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


@Slf4j
@Component
public class LogInterceptor implements HandlerInterceptor {

    @Autowired
    private MetricChanger metricChanger;

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        String ip = request.getRemoteAddr();
        String req = request.getRequestURI().substring(1);
        String originQuery = request.getQueryString();
        StringBuilder query = new StringBuilder();
        if(originQuery != null) {
            String[] querys = originQuery.split("&");
            for(String s : querys) {
                if(s.contains("password")) {
                    s="password=********";
                }
                query.append(s);
                query.append("&");
            }
            query = new StringBuilder(query.substring(0, query.length() - 1));
        }
        if(!req.equals("metrics")) {
            log.info("IP : " + ip + " / REQ : " + req + " / Query : " + query);
        } else {
            metricChanger.run();
            log.debug("IP : " + ip + " / REQ : " + req + " / Query : " + query);
        }
        String referer = StringUtils.defaultString(request.getHeader("Referer"),"-");
        String agent = StringUtils.defaultString(request.getHeader("User-Agent"),"-");
        log.debug("Referer : " + referer);
        log.debug("Agent : " + agent);
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }
}

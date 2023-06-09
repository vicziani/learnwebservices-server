package com.learnwebservices.services;

import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class SetMdcFilter implements Filter {

    public static final String USER_AGENT_KEY = "user-agent";
    public static final String REMOTE_IP_KEY = "remote-ip";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String userAgent = ((HttpServletRequest)servletRequest).getHeader("User-Agent");
        MDC.put(USER_AGENT_KEY, userAgent);

        String remoteIp = ((HttpServletRequest)servletRequest).getHeader("X-Real-IP");
        if (remoteIp == null) {
            remoteIp = servletRequest.getRemoteAddr();
        }
        MDC.put(REMOTE_IP_KEY, remoteIp);

        filterChain.doFilter(servletRequest, servletResponse);

        MDC.remove(USER_AGENT_KEY);
        MDC.remove(REMOTE_IP_KEY);
    }
}

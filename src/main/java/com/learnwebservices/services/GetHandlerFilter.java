package com.learnwebservices.services;

import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Component
public class GetHandlerFilter implements Filter {

    private static final List<String> urls = List.of("hello");

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        var httpRequest = (HttpServletRequest) request;
        var httpResponse = (HttpServletResponse) response;
        if (serviceOnUrl(httpRequest) && methodIsGet(httpRequest) && notContainsWsdlParameter(httpRequest)) {
            httpResponse.setContentType("text/plain");
            httpResponse.setStatus(405);
            httpResponse.getWriter().write("Method Not Allowed, please use http POST method instead of GET");
        }
        else {
            chain.doFilter(request, response);
        }
    }

    private boolean methodIsGet(HttpServletRequest httpRequest) {
        return httpRequest.getMethod().equalsIgnoreCase("get");
    }

    private boolean serviceOnUrl(HttpServletRequest httpRequest) {
        String uri = httpRequest.getRequestURI();
        return urls.stream().map(s -> "/services/" + s).anyMatch(s -> s.equals(uri));
    }

    private boolean notContainsWsdlParameter(HttpServletRequest httpRequest) {
        return httpRequest.getQueryString() == null || !httpRequest.getQueryString().toLowerCase().contains("wsdl");
    }
}

package com.learnwebservices;

import com.learnwebservices.services.FaultInterceptor;
import com.learnwebservices.services.hello.CorsProperties;
import com.learnwebservices.services.hello.HelloEndpoint;
import com.learnwebservices.services.tempconverter.TempConverterEndpoint;
import org.apache.cxf.Bus;
import org.apache.cxf.ext.logging.LoggingFeature;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import jakarta.xml.ws.Endpoint;
import java.util.List;
import java.util.Map;


@SpringBootApplication
@Configuration
@EnableConfigurationProperties(CorsProperties.class)
public class LearnWebservicesApp {

    public static void main(String[] args) {
        SpringApplication.run(LearnWebservicesApp.class, args);
    }

    @Autowired
    private Bus bus;

    @Autowired
    private Environment environment;

    @Bean
    public ApplicationListener<ApplicationStartedEvent> initBus() {
        return this::setupBus;
    }

    public void setupBus(ApplicationStartedEvent event) {
        LoggingFeature loggingFeature = new LoggingFeature();
        loggingFeature.setPrettyLogging(true);
        bus.setFeatures(List.of(loggingFeature));
    }

    @Bean
    public Endpoint endpoint(HelloEndpoint helloEndpoint) {
        EndpointImpl endpoint = new EndpointImpl(bus, helloEndpoint);
        endpoint.publish("/hello");
        return endpoint;
    }

    @Bean
    public Endpoint publishedTempConverterEndpoint(TempConverterEndpoint tempConverterEndpoint) {
        EndpointImpl endpoint = new EndpointImpl(bus, tempConverterEndpoint);
        endpoint.setOutFaultInterceptors(List.of(new FaultInterceptor()));
        endpoint.publish("/tempconverter");
        return endpoint;
    }

    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilter(CorsProperties corsProperties) {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(corsProperties.getAllowedOrigins());
        config.setAllowedMethods(List.of("GET", "POST"));
        source.registerCorsConfiguration("/hello/**", config);
        FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(new CorsFilter(source));
        bean.setOrder(0);
        return bean;
    }

    @Bean
    public ServletRegistrationBean<CXFServlet> cxfServlet() {
        ServletRegistrationBean<CXFServlet> servletRegistrationBean = new ServletRegistrationBean<>(new CXFServlet(), "/services/*");
        servletRegistrationBean.setInitParameters(Map.of("use-x-forwarded-headers", "true"));
        return servletRegistrationBean;
    }

}

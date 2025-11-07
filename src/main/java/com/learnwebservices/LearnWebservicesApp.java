package com.learnwebservices;

import com.learnwebservices.services.FaultInterceptor;
import com.learnwebservices.services.hello.CorsProperties;
import com.learnwebservices.services.hello.HelloEndpoint;
import lombok.AllArgsConstructor;
import org.apache.cxf.Bus;
import org.apache.cxf.ext.logging.LoggingFeature;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import jakarta.xml.ws.Endpoint;
import org.springframework.web.filter.ForwardedHeaderFilter;

import java.util.List;

@SpringBootApplication
@Configuration
@EnableConfigurationProperties(CorsProperties.class)
@AllArgsConstructor
public class LearnWebservicesApp {

    public static void main(String[] args) {
        SpringApplication.run(LearnWebservicesApp.class, args);
    }

    private Bus bus;

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
    @Order(0)
    public ForwardedHeaderFilter forwardedHeaderFilter() {
        return new ForwardedHeaderFilter();
    }

    @Bean
    @Order(1)
    public CorsFilter corsFilter(CorsProperties corsProperties) {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(corsProperties.getAllowedOrigins());
        config.setAllowedMethods(List.of("GET", "POST"));
        source.registerCorsConfiguration("/hello/**", config);
        return new CorsFilter(source);
    }

}

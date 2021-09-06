package com.learnwebservices.services.hello;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties(prefix = "application.cors")
@Data
public class CorsProperties {

    private List<String> allowedOrigins;
}

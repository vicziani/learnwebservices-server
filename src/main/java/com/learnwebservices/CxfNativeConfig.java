package com.learnwebservices;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportRuntimeHints;

@ImportRuntimeHints(CxfResourceHints.class)
@Configuration
public class CxfNativeConfig {
}

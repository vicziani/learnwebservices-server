package com.learnwebservices.services.tempconverter;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TempConverterIT {

    @LocalServerPort
    int port;

    TempConverterEndpoint endpoint;

    @BeforeEach
    void init() {
        var proxyFactory = new JaxWsProxyFactoryBean();
        proxyFactory.setServiceClass(TempConverterEndpoint.class);
        proxyFactory.setAddress("http://localhost:" + port + "/services/tempconverter");

        endpoint = proxyFactory.create(TempConverterEndpoint.class);
    }

    @Test
    void testConvertToCelsius() {
        var request = new FahrenheitToCelsiusRequest(0);
        assertEquals(-17.778, endpoint.convertToCelsius(request).getTemperatureInCelsius(), 0.0005);
    }

    @Test
    void testConvertToFahrenheit() {
        var request = new CelsiusToFahrenheitRequest(0);
        assertEquals(32.0, endpoint.convertToFahrenheit(request).getTemperatureInFahrenheit(), 0.00005);
    }
}

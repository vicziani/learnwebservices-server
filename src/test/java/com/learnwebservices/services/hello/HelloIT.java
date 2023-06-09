package com.learnwebservices.services.hello;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HelloIT {

    @LocalServerPort
    int port;

    @Test
    void testHello() {
        var proxyFactory = new JaxWsProxyFactoryBean();
        proxyFactory.setServiceClass(HelloEndpoint.class);
        proxyFactory.setAddress("http://localhost:" + port + "/services/hello");

        var endpoint = proxyFactory.create(HelloEndpoint.class);

        var request = new HelloRequest("John Doe");
        var response = endpoint.sayHello(request);

        assertEquals("Hello John Doe!", response.getMessage());
    }
}

package com.learnwebservices.services.hello;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HelloEndpointTest {

    @Test
    void testSayHello() {
        var helloEndpoint = new SimpleHelloEndpoint();
        var response = helloEndpoint.sayHello(new HelloRequest("John Doe"));
        assertEquals("Hello John Doe!", response.getMessage());
    }

}

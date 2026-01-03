package com.learnwebservices.services.hello;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.nio.charset.StandardCharsets;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HelloWebClientIT {

    @Autowired
    WebTestClient webClient;

    @Test
    void hello() {
        // language=XML
        String request = """
                <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:hel="http://learnwebservices.com/services/hello">
                   <soapenv:Header/>
                   <soapenv:Body>
                      <hel:HelloRequest>
                         <hel:Name>John Doe</hel:Name>
                      </hel:HelloRequest>
                   </soapenv:Body>
                </soapenv:Envelope>
                """;

        // language=XML
        String response = """
                <soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
                   <soap:Body>
                      <HelloResponse xmlns="http://learnwebservices.com/services/hello">
                         <Message>Hello John Doe!</Message>
                      </HelloResponse>
                   </soap:Body>
                </soap:Envelope>
                """;

        webClient
                .post()
                .uri("/services/hello")
                .contentType(MediaType.APPLICATION_XML)
                .header("SOAPAction", "")
                .bodyValue(request)
                .exchange()
                .expectStatus().isOk()
                .expectBody().xml(response);
    }

    @Test
    void services() {
        webClient
                .get()
                .uri("/services")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .consumeWith(result -> new String(result.getResponseBody(), StandardCharsets.UTF_8).contains("HelloEndpoint"));
    }

    @Test
    void wsdl() {
        webClient
                .get()
                .uri("/services/hello?wsdl")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .xpath("definitions/service/@name").isEqualTo("HelloEndpointService");
    }
}

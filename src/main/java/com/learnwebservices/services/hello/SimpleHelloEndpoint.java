package com.learnwebservices.services.hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import jakarta.jws.WebService;
import jakarta.xml.ws.WebServiceContext;
import jakarta.xml.ws.handler.MessageContext;
import java.util.List;
import java.util.Map;

@Service
@WebService(targetNamespace = "http://learnwebservices.com/services/hello",
        serviceName = "HelloEndpointService", portName = "HelloEndpointPort")
public class SimpleHelloEndpoint implements HelloEndpoint {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private WebServiceContext context;

    public HelloResponse sayHello(HelloRequest request) {
        logger.debug("HelloRequest from '{}' with name '{}'", getUserAgent(), request.getName());
        return new HelloResponse(String.format("Hello %s!", request.getName()));
    }

    private String getUserAgent() {
        if ((context != null) && (context.getMessageContext() != null) &&
                (context.getMessageContext().get(MessageContext.HTTP_REQUEST_HEADERS) != null)) {
            return ((Map<String, List<String>>)context.getMessageContext().get(MessageContext.HTTP_REQUEST_HEADERS))
                    .entrySet().stream().filter(e -> e.getKey().equalsIgnoreCase("user-agent"))
                    .flatMap(e -> e.getValue().stream()).findFirst().orElse(null);
        }
        else {
            return null;
        }
    }

}

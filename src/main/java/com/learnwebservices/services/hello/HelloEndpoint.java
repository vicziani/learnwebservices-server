package com.learnwebservices.services.hello;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.xml.bind.annotation.XmlElement;

@WebService(targetNamespace = "http://learnwebservices.com/services/hello")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface HelloEndpoint {

    @WebMethod(operationName = "SayHello")
    @WebResult(name = "HelloResponse")
    @XmlElement(required = true)
    HelloResponse sayHello(@WebParam(name = "HelloRequest") @XmlElement(required = true) HelloRequest request);
}

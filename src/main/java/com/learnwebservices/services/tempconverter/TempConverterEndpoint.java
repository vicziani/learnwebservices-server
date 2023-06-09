package com.learnwebservices.services.tempconverter;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.xml.bind.annotation.XmlElement;

@WebService(targetNamespace = "http://learnwebservices.com/services/tempconverter")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface TempConverterEndpoint {

    @WebMethod(operationName = "CelsiusToFahrenheit")
    @WebResult(name = "CelsiusToFahrenheitResponse")
    @XmlElement(required = true)
    CelsiusToFahrenheitResponse convertToFahrenheit(@WebParam(name = "CelsiusToFahrenheitRequest") @XmlElement(required = true)CelsiusToFahrenheitRequest request);


    @WebMethod(operationName = "FahrenheitToCelsius")
    @WebResult(name = "FahrenheitToCelsiusResponse")
    @XmlElement(required = true)
    FahrenheitToCelsiusResponse convertToCelsius(@WebParam(name = "FahrenheitToCelsiusRequest") @XmlElement(required = true)FahrenheitToCelsiusRequest request);
}

package com.learnwebservices.services;

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.binding.soap.interceptor.AbstractSoapInterceptor;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.Phase;

public class FaultInterceptor extends AbstractSoapInterceptor {

    public FaultInterceptor() {
        super(Phase.PRE_STREAM);
    }

    @Override
    public void handleMessage(SoapMessage message) {
        Exception exception = message.getContent(Exception.class);
        if (exception instanceof Fault && isWrongNumber(exception)) {
            ((Fault) exception).setMessage(exception.getMessage() +
                    " If you use comma (,) character for decimal separator, change it to dot (.) character!");
        }
    }

    private boolean isWrongNumber(Throwable exception) {
        while (exception.getCause() != null) {
            exception = exception.getCause();
        }
        return  exception instanceof NumberFormatException;
    }


}

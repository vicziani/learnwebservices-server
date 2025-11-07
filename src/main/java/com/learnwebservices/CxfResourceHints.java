package com.learnwebservices;

import com.ibm.wsdl.extensions.schema.SchemaImpl;
import com.learnwebservices.services.hello.HelloEndpoint;
import com.learnwebservices.services.hello.HelloRequest;
import com.learnwebservices.services.hello.HelloResponse;
import com.learnwebservices.services.hello.SimpleHelloEndpoint;
import org.apache.cxf.catalog.OASISCatalogManager;
import org.apache.cxf.ext.logging.LoggingBusLifecycleListener;
import org.apache.cxf.io.DelayedCachedOutputStreamCleaner;
import org.apache.cxf.jaxws.spi.WrapperClassNamingConvention;
import org.springframework.aot.hint.MemberCategory;
import org.springframework.aot.hint.RuntimeHints;
import org.springframework.aot.hint.RuntimeHintsRegistrar;

public class CxfResourceHints implements RuntimeHintsRegistrar {

    @Override
    public void registerHints(RuntimeHints hints, ClassLoader classLoader){
        hints.resources().registerPattern("org/apache/cxf/**/*.properties");
        hints.reflection().registerType(WrapperClassNamingConvention.DefaultWrapperClassNamingConvention.class, MemberCategory.INVOKE_DECLARED_CONSTRUCTORS);
        hints.reflection().registerType(DelayedCachedOutputStreamCleaner.class, MemberCategory.INVOKE_DECLARED_CONSTRUCTORS);
        hints.reflection().registerType(SchemaImpl.class, MemberCategory.INVOKE_DECLARED_CONSTRUCTORS);
        hints.reflection().registerType(OASISCatalogManager.class, MemberCategory.INVOKE_DECLARED_CONSTRUCTORS);
        hints.reflection().registerType(LoggingBusLifecycleListener.class, MemberCategory.INVOKE_DECLARED_CONSTRUCTORS);
        hints.reflection().registerType(SimpleHelloEndpoint.class,
                MemberCategory.DECLARED_FIELDS,
                MemberCategory.INVOKE_DECLARED_CONSTRUCTORS,
                MemberCategory.INVOKE_DECLARED_METHODS,
                MemberCategory.INTROSPECT_DECLARED_CONSTRUCTORS,
                MemberCategory.INTROSPECT_DECLARED_METHODS);
        hints.reflection().registerType(HelloEndpoint.class,
                MemberCategory.INVOKE_DECLARED_CONSTRUCTORS,
                MemberCategory.INVOKE_DECLARED_METHODS,
                MemberCategory.INTROSPECT_DECLARED_CONSTRUCTORS,
                MemberCategory.INTROSPECT_DECLARED_METHODS);
        hints.reflection().registerType(HelloRequest.class,
                MemberCategory.DECLARED_FIELDS,
                MemberCategory.INVOKE_DECLARED_CONSTRUCTORS,
                MemberCategory.INVOKE_DECLARED_METHODS,
                MemberCategory.INTROSPECT_DECLARED_CONSTRUCTORS,
                MemberCategory.INTROSPECT_DECLARED_METHODS);
        hints.reflection().registerType(HelloResponse.class,
                MemberCategory.DECLARED_FIELDS,
                MemberCategory.INVOKE_DECLARED_CONSTRUCTORS,
                MemberCategory.INVOKE_DECLARED_METHODS,
                MemberCategory.INTROSPECT_DECLARED_CONSTRUCTORS,
                MemberCategory.INTROSPECT_DECLARED_METHODS);
    }
}

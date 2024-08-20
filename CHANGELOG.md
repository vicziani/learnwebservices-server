# Changelog

## [3.0.1]

- Use constructor injection in `LearnWebservicesApp`
- Register `CorsFilter` directly without `FilterRegistrationBean`
- Use `ForwardedHeaderFilter` instead of configuring `ServletRegistrationBean`

## [3.0.0]

- Upgrade to Spring 3.1.0, Java 17 and CXF 4.0.1
- CXF handles the `X-Forwarded-*` headers during WSDL generation
- `.http` test file

## [2.0.1]

- Multi-platform image (`linux/amd64`, `linux/arm64`)

## [2.0.0]

- Move to Amazon Web Services
- Simplify WSDL and SOAP messages in `hello` web service, `SOAPBinding.ParameterStyle.BARE` in `HelloEndpoint`
- Remove `setPublishedEndpointUrl`, CXF detects it automatically
- Remove public and private modifiers from test classes
- Remove `SpringExtension` from test classes, `@SpringBootTest` is enough
- New test with WebTestClient
- Obvious text in `index.html`

## [1.0.4]

- Application generates correct WSDL files
- TempConverter service doesn't use embedded tags
- Dockerize
- Build with GitHub actions
- CORS support
- Downgrade to latest LTS Java version
- Log to Sematext
- Remote ip appears in the log with SLF4J MDC  
- Filter log messages by remote ip 

## [1.0.3]

- Payload logging
- Separate unit and integration tests

## [1.0.2]

- Spring Boot Actuator with `info` and `health` endpoints (with Maven build info)

## [1.0.1]

- Remove `System.out`
- Upgrade to Java 12
- Introduce Project Lombok
- JUnit 5
- 3rd party libraries version updates

## [1.0.0]

- Initial release
- Sample `hello` and `tempconverter` web services
- Unit and integration tests
- JavaScript client
- Logs requests and `User-Agent` header
- Logs to sematext
- Handles GET method with status 405 Method not allowed
- Returns hint when using bad decimal separator when calling tempconverter

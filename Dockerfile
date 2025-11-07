FROM eclipse-temurin:21 AS builder
WORKDIR /application
COPY target/*.jar application.jar
RUN java -Djarmode=tools -jar employees.jar extract --layers --destination extracted

FROM eclipse-temurin:21
RUN apt update && apt install -y busybox && rm -rf /var/lib/apt/lists/*
WORKDIR /application
COPY --from=builder application/dependencies/ ./
COPY --from=builder application/spring-boot-loader/ ./
COPY --from=builder application/snapshot-dependencies/ ./
COPY --from=builder application/application/ ./
HEALTHCHECK CMD busybox wget -qO- http://localhost:8080/actuator/health || exit 1
ENTRYPOINT ["java", "-jar", "employees.jar"]
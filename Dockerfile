FROM eclipse-temurin:21 AS builder
WORKDIR /application
COPY target/*.jar lwsapp.jar
RUN java -Djarmode=tools -jar lwsapp.jar extract --layers --destination extracted

FROM eclipse-temurin:21
RUN apt update && apt install -y busybox && rm -rf /var/lib/apt/lists/*
WORKDIR /application
COPY --from=builder application/extracted/dependencies/ ./
COPY --from=builder application/extracted/spring-boot-loader/ ./
COPY --from=builder application/extracted/snapshot-dependencies/ ./
COPY --from=builder application/extracted/application/ ./
HEALTHCHECK CMD busybox wget -qO- http://localhost:8080/actuator/health || exit 1
ENTRYPOINT ["java", "-jar", "lwsapp.jar"]
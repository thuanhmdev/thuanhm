# Stage 1: Build the application
FROM maven:3.9.9-eclipse-temurin-21-alpine AS build-stage
COPY --chown=maven:maven . /appBE
WORKDIR /appBE

#skip task: test
#RUN mvn clean package -DskipTests
RUN mvn clean package -P prod -DskipTests

FROM openjdk:21-slim

COPY --from=build-stage /appBE/target/*.jar /appBE/app.jar
ENTRYPOINT ["java", "-jar", "/appBE/app.jar"]

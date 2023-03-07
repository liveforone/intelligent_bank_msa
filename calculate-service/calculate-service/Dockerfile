FROM openjdk:17-jdk-slim
VOLUME /tmp
ARG JAR_FILE=./build/libs/calculate-service-1.0.jar
COPY ${JAR_FILE} CalculateService.jar
ENTRYPOINT ["java","-jar","CalculateService.jar"]
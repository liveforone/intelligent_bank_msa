FROM openjdk:17-jdk-slim
VOLUME /tmp
ARG JAR_FILE=./build/libs/atm-service-1.0.jar
COPY ${JAR_FILE} AtmService.jar
ENTRYPOINT ["java","-jar","AtmService.jar"]
FROM openjdk:11 AS builder
VOLUME /tmp
WORKDIR app
ARG APPJAR=target/*.jar
COPY ${APPJAR} app.jar

EXPOSE 8080

CMD ["java","-jar","app.jar"]
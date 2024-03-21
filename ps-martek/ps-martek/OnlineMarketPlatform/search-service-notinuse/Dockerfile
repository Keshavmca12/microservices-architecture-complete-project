# For Java 8, try this
# FROM openjdk:8-jdk-alpine

# For Java 11, try this
FROM adoptopenjdk/openjdk11:alpine-jre

# Refer to Maven build -> finalName
ARG JAR_FILE=target/search-service-1.0-SNAPSHOT.jar

# Add a new user "csp-service-account" with user id 8877

#RUN addgroup -S coegroup && adduser -u 8877 -S coe-service-account -G coegroup

# Change to non-root privilege
#USER coe-service-account

# cd /opt/app
#WORKDIR /home/coe-service-account
WORKDIR /opt/app

# cp target/spring-boot-web.jar /opt/app/app.jar
COPY ${JAR_FILE} app.jar

# java -jar /opt/app/app.jar
ENTRYPOINT ["java","-jar","app.jar"]
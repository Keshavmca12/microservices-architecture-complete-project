FROM azul/zulu-openjdk-alpine:14
MAINTAINER prashantjain
ARG JAR_FILE=target/marktek-iam-1.0-SNAPSHOT.jar
COPY ${JAR_FILE} /app/marktek-iam.jar
ENTRYPOINT ["java","-Dspring.profiles.active=local","-jar","/app/marktek-iam.jar"]
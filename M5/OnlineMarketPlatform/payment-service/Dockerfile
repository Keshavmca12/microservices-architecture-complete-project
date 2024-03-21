FROM eclipse-temurin:17-jdk-jammy
EXPOSE 8080
ADD target/payment-service-1.0-SNAPSHOT.jar payment-service.jar
ENTRYPOINT ["java","-jar","/payment-service.jar"]
FROM eclipse-temurin:17-jdk-jammy
EXPOSE 8080
ADD target/order-service-1.0-SNAPSHOT.jar order-service.jar
ENTRYPOINT ["java","-jar","/order-service.jar"]
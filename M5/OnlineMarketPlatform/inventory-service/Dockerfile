FROM eclipse-temurin:17-jdk-jammy
EXPOSE 8080
ADD target/inventory-service-1.0-SNAPSHOT.jar inventory-service.jar
ENTRYPOINT ["java","-jar","/inventory-service.jar"]
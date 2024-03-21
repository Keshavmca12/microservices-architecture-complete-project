FROM eclipse-temurin:17-jdk-jammy
EXPOSE 8080
ADD target/inventory-service-2.0-SNAPSHOT.jar product-service.jar
ENTRYPOINT ["java","-jar","/product-service.jar"]
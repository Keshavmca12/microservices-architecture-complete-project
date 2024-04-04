# Use an official OpenJDK runtime as a parent image
FROM adoptopenjdk/openjdk11:alpine-jre

# set shell to bash
# source: https://stackoverflow.com/a/40944512/3128926
RUN apk update && apk add bash

# Set the working directory to /app
WORKDIR /app

# Copy the fat jar into the container at /app
COPY /target/customer-service-1.0.0.jar /app

# Make port 6061 available to the world outside this container
EXPOSE 6061

# Run jar file when the container launches
CMD ["java", "-jar", "customer-service-1.0.0.jar"]

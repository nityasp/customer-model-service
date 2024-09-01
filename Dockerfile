# Start with a base image containing Java runtime
FROM openjdk:17-jdk-alpine

# Add Maintainer Info
LABEL authors="Nitya"

# Add a volume pointing to /tmp
VOLUME /tmp

EXPOSE 8089

# The application's jar file
ARG JAR_FILE=target/customer-model-1.0.jar

# Add the application's jar to the container
COPY ${JAR_FILE} customer-model-1.0.jar

# Run the jar file
ENTRYPOINT ["java","-jar","/customer-model-1.0.jar"]

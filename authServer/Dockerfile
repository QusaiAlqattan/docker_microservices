# Use a base image with the correct Java version (Java 22)
FROM openjdk:22-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the Maven build artifact (JAR file) into the container
COPY target/authServer-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8081

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]

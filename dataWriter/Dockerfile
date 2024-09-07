# Use a base image with the correct Java version (Java 22)
FROM openjdk:22-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the Maven build artifact (JAR file) into the container
COPY target/dataWriter-0.0.1-SNAPSHOT.jar app.jar

# Expose the port on which the app will run
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]

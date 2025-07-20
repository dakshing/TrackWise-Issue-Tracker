# Use Maven image to build the application
FROM maven:3.9.6-eclipse-temurin-17 AS builder

WORKDIR /app
COPY . .

# Run Maven build (this creates the .jar file)
RUN mvn clean package -DskipTests

# Use a minimal JDK base image to run the app
FROM openjdk:17-jdk-slim

WORKDIR /app
COPY --from=builder /app/identity-service/target/identity-service-1.0.0.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]

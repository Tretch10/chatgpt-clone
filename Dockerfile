# Build stage: use Maven to build Spring Boot app with proper executable jar
FROM maven:3.9.3-eclipse-temurin-17 as builder
WORKDIR /app

# Copy pom and download dependencies first (cache efficient)
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy source and build with spring-boot:repackage
COPY src ./src
RUN mvn clean package spring-boot:repackage -DskipTests

# Run stage: use lightweight JDK to run jar
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app

# Copy built executable jar from builder stage
COPY --from=builder /app/target/*.jar app.jar

# Expose port (optional, helpful for local)
EXPOSE 8080

# Start the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]

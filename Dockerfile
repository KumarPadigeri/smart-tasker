# Stage 1: Build the app using Amazon Corretto (Alpine)
FROM amazoncorretto:17-alpine3.18 AS builder

WORKDIR /app

# Copy all files to container
COPY . .

# Make mvnw executable
RUN chmod +x ./mvnw

# Build the application (skip tests for faster build)
RUN ./mvnw clean package -DskipTests

# Stage 2: Use a minimal runtime image
FROM amazoncorretto:17-alpine3.18

WORKDIR /app

# Copy the built JAR from the builder stage
COPY --from=builder /app/target/smart-tasker-0.0.1-SNAPSHOT.jar app.jar

# Expose port 8080
EXPOSE 8080

# Run the app
ENTRYPOINT ["java", "-jar", "app.jar"]

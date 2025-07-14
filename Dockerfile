# Stage 1: Build the app using Amazon Corretto (ARM64)
FROM amazoncorretto:17-alpine3.18 as builder

WORKDIR /app

# Copy everything into the container
COPY . .

# Build the application (skip tests for faster build)
RUN ./mvnw clean package -DskipTests

# Stage 2: Use a minimal runtime image
FROM amazoncorretto:17-alpine3.18

WORKDIR /app

# Copy only the final JAR from the builder stage
COPY --from=builder /app/target/smart-tasker-0.0.1-SNAPSHOT.jar app.jar

# Expose the application port
EXPOSE 8080

# Run the app
ENTRYPOINT ["java", "-jar", "app.jar"]

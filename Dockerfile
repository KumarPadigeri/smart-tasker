FROM amazoncorretto:17-alpine3.18 AS builder

WORKDIR /app

COPY . .

# Make mvnw executable
RUN chmod +x ./mvnw

# Build the application (skip tests for faster build)
RUN ./mvnw clean package -DskipTests

# Stage 2: Use a minimal runtime image
FROM amazoncorretto:17-alpine3.18

WORKDIR /app

COPY --from=builder /app/target/smart-tasker-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]

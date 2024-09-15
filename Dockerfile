# Build stage
FROM maven:3.8.5-openjdk-17 AS builder

WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src
RUN mvn clean package

# Production stage
FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY --from=builder /app/target/*.jar .
RUN apk add --no-cache ca-certificates && update-ca-certificates

CMD ["java", "-jar", "hiring-test-1.0-SNAPSHOT.jar"]

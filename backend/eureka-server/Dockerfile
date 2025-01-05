# Stage 1: Build the application
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package -DskipTests
# Stage 2: Run the application
FROM openjdk:17-jdk-slim
ARG VERSION=0.0.1-SNAPSHOT
WORKDIR /app
ENV JAR_VERSION=${VERSION}
# Copy the pre-built jar from the Docker context
COPY --from=build /app/target/eureka-server-${JAR_VERSION}.jar /app/
# Set environment variable for app version
# Run the application
CMD java -jar eureka-server-${JAR_VERSION}.jar

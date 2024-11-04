# Stage 2: Run the application
FROM openjdk:17-jdk-slim
ARG VERSION=0.0.1-SNAPSHOT
WORKDIR /app
ENV JAR_VERSION=${VERSION}
# Copy the pre-built jar from the Docker context
COPY ./target/eureka-server-${JAR_VERSION}.jar /app/
# Set environment variable for app version
# Run the application
CMD java -jar eureka-server-${JAR_VERSION}.jar

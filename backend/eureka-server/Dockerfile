# Stage 2: Run the application
FROM openjdk:17-jdk-slim
ARG VERSION=1.0.0
WORKDIR /app
# Copy the pre-built jar from the Docker context
COPY target/eureka-server-*.jar /app/
# Set environment variable for app version

ENV JAR_VERSION=${VERSION}
# Run the application
CMD java -jar eureka-server-${JAR_VERSION}.jar

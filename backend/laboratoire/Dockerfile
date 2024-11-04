# Stage 2: Run the application
FROM openjdk:17-jdk-slim
ARG VERSION=1.0.0
WORKDIR /app
# Copy the pre-built jar from the Docker context
COPY target/laboratoire-0.0.1-SNAPSHOT.jar /app/
# Set environment variable for app version


# Run the application
CMD java -jar laboratoire-0.0.1-SNAPSHOT.jar

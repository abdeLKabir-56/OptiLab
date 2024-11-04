# Stage 2: Run the application
FROM openjdk:17-jdk-slim
ARG VERSION=0.0.1-SNAPSHOT
ARG PROFILE=docker


WORKDIR /app
ENV JAR_VERSION=${VERSION}
# Copy the pre-built jar from the Docker context
COPY ./target/laboratoire-${JAR_VERSION}.jar /app/
# Set environment variable for app version
ENV JAR_VERSION=${VERSION}
ENV ACTIVE_PROFILE=${PROFILE}

# Run the application
CMD java -jar -Dspring.profiles.active=${ACTIVE_PROFILE} laboratoire-${JAR_VERSION}.jar

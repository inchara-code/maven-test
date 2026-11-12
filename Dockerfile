# Use official Maven image with OpenJDK 17
FROM maven:3.9.6-eclipse-temurin-17

# Set working directory inside the container
WORKDIR /app

# Copy pom.xml first to leverage Docker layer caching for dependencies
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy the source code
COPY src ./src

# Run JUnit 5 tests and build the application
RUN mvn clean test

# Default command when running the container
CMD ["mvn", "test"]
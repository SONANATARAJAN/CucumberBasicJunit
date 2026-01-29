# Use Maven + JDK image
FROM maven:3.9.2-eclipse-temurin-20

# Set working directory
WORKDIR /app

# Copy pom.xml and source code
COPY pom.xml .
COPY src ./src

# Optional: pre-download dependencies
RUN mvn dependency:go-offline

# Default command
CMD ["mvn", "test"]

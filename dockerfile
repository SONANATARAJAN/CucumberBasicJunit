FROM selenium/standalone-chrome:latest

# Install Maven
USER root
RUN apt-get update && \
    apt-get install -y maven && \
    rm -rf /var/lib/apt/lists/*

# Set working directory
WORKDIR /app

# Copy project
COPY pom.xml .
COPY src ./src

# Pre-download dependencies
RUN mvn dependency:go-offline

# Default command
CMD ["mvn", "test"]

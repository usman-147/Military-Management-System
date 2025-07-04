# Use OpenJDK 21 as base image
FROM eclipse-temurin:21-jdk

# Set working directory
WORKDIR /app

# Copy Maven files first to leverage Docker caching
COPY backend/pom.xml backend/mvnw backend/.mvn/ /app/
COPY backend/src /app/src

# Install Maven and build the app
RUN ./mvnw clean package -DskipTests

# Run the JAR
CMD ["java", "-jar", "target/backend-0.0.1-SNAPSHOT.jar"]

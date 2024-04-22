FROM eclipse-temurin:17-jdk-jammy
# Set the working directory inside the container
WORKDIR /app
# Copy the application's JAR file into the working directory
COPY target/QuoteAI-1.0-SNAPSHOT.jar /app/app.jar
# Application startup command
ENTRYPOINT ["java","-jar","app.jar"]
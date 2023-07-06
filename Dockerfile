# Use a base image with Java 17
FROM adoptopenjdk:17-jdk-hotspot

# Set the working directory inside the container
WORKDIR /app

RUN apt-get update && apt-get install -y maven

# Copy the Maven project file to the container
COPY pom.xml .

# Download the project dependencies
RUN mvn dependency:go-offline -B

# Copy the application source code to the container
COPY src ./src

# Build the application
RUN mvn package -DskipTests

# Set the entrypoint command to run the application
ENTRYPOINT ["java", "-jar", "target/zitona-0.0.1-SNAPSHOT.jar"]

# Stage 1: Build the application
FROM maven:3.8.1-openjdk-17 AS build
WORKDIR /app/otp
COPY pom.xml .

RUN mvn dependency:go-offline
COPY ./src ./src
RUN mvn clean package -DskipTests
# Stage 2: Run the application
FROM adoptopenjdk:17-jre-hotspot
WORKDIR /app/otp
COPY --from=build /app/otp/target/otp-0.0.1-SNAPSHOT.jar /app/otp/otp.jar
EXPOSE 8080
CMD ["java", "-jar", "otp.jar"]

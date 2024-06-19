FROM maven:3.8.5-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17-ea-18-jdk-slim
COPY --from=build /target/number-processor-0.0.1-SNAPSHOT.jar number-processor.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "number-processor.jar"]
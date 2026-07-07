FROM maven:3.9-amazoncorretto-17 AS build
WORKDIR /app
COPY pom.xml ./
RUN mvn dependency:resolve
COPY src ./src
RUN mvn package

FROM amazoncorretto:17
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]

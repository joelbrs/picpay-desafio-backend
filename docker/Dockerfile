FROM maven:3.9.6-eclipse-temurin-21 as build

WORKDIR /app
COPY . .

RUN mvn dependency:go-offline
RUN mvn clean package -DskipTests

FROM amazoncorretto:21
WORKDIR /app

COPY --from=build /app/application/target/*.jar /app/application.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "application.jar"]

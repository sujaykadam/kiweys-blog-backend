FROM openjdk:17
WORKDIR /server

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src

RUN ./mvnw package
COPY target/*.jar server.jar
ENTRYPOINT ["java", "-jar", "server.jar"]
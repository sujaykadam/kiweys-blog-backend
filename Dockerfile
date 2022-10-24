FROM openjdk:17 as buildstage
WORKDIR /server

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src
COPY .env .env

RUN ./mvnw package

ENTRYPOINT ["java", "-jar", "target/kiweys-blog-backend.jar"]
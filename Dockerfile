FROM openjdk:17 as buildstage
WORKDIR /server

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src

RUN ./mvnw package

ENTRYPOINT ["java", "-jar", "target/kiweys-blog-backend.jar"]
# Build Stage
FROM maven:3.8.6-eclipse-temurin-17-focal AS build
WORKDIR /home/app
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

# Package Stage
FROM eclipse-temurin:17-jre-focal
WORKDIR /usr/local/lib
COPY --from=build /home/app/target/bookstore.jar /usr/local/lib/bookstore.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "bookstore.jar"]

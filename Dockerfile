
FROM maven:3.8.6-eclipse-temurin-17-focal AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src /app/src
RUN mvn package


FROM eclipse-temurin:17-jre-focal
COPY --from=build /app/booksrore.jar /usr/local/lib/bookstore.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/usr/local/lib/bookstore.jar"]

FROM eclipse-temurin:17-jdk-jammy
WORKDIR /meanservice
COPY ./.mvn .mvn
COPY ./pom.xml .
COPY ./mvnw .
RUN chmod +x mvnw
RUN ./mvnw dependency:resolve
COPY ./src src
CMD ["./mvnw", "spring-boot:run"]

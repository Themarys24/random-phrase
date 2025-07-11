# Etapa 1: Build com Maven + Java 17
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline

# Copiar código fonte e fazer build
COPY src ./src
RUN mvn clean package -DskipTests

# Etapa 2: Imagem mínima para rodar o .jar
FROM openjdk:17.0.1-jdk-slim
WORKDIR /app
COPY --from=build /app/target/randomPhrase-0.0.1-SNAPSHOT.jar demo.jar
EXPOSE 8080
CMD ["java", "-jar", "demo.jar"]



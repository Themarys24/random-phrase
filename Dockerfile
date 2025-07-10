# Etapa 1: Build com Maven + Java 21
FROM maven:3.8.5-openjdk AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Etapa 2: Imagem mínima para rodar o .jar
FROM openjdk:17.0.1-jdk-slim
WORKDIR /app
COPY --from=build /target/demo-0.0.1-SNAPSHOT.jar demo.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "demo.jar"]

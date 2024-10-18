# Etapa 1: Build da aplicação com Maven
FROM maven:3.9.8-eclipse-temurin-22 AS build  # Atualizado para JDK 22
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Etapa 2: Executar a aplicação no container
FROM eclipse-temurin:22-jre-alpine  # Atualizado para JDK 22
WORKDIR /app
COPY --from=build /app/target/gestao-residuos-0.0.1-SNAPSHOT.jar /app/app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]

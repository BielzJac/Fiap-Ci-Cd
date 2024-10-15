# Build Stage
FROM maven:3.8.1-openjdk-21 AS build  # Alterado para JDK 21
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Run Stage
FROM openjdk:21-jre-slim  # Alterado para JDK 21

# Informações do MySQL
ENV SPRING_DATASOURCE_URL=jdbc:mysql://localhost:3306/db_gestaoresiduos?createDatabaseIfNotExist=true
ENV SPRING_DATASOURCE_USERNAME=root
ENV SPRING_DATASOURCE_PASSWORD=1234
ENV SPRING_DATASOURCE_DRIVER_CLASS_NAME=com.mysql.cj.jdbc.Driver

# Copie o JAR construído
COPY --from=build /app/target/gestao-residuos-0.0.1-SNAPSHOT.jar app.jar

# Expor a porta da aplicação
EXPOSE 8080

# Defina o comando de execução
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app.jar"]

# Build Stage
FROM maven:3.9.3-eclipse-temurin-21 AS build  # Usando imagem que suporta Maven e JDK 21
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Run Stage
FROM eclipse-temurin:21-jre  # Certificar que o JRE é da mesma versão do JDK utilizado na build

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

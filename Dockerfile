# Etapa 1: Compilacion y pruebas
FROM maven:3.8.3-openjdk-17 AS build

# Directorio de trabajo
WORKDIR /app

# Copiamos el archivo POM y los archivos fuente
COPY pom.xml .
COPY src ./src

# Empaquetamos la aplicacion y ejecutamos las pruebas
RUN mvn clean package

# Etapa 2: Construccion de la imagen final
FROM openjdk:17.0.1-jdk-slim

# Copiamos el archivo JAR desde la etapa de compilacion
COPY --from=build /app/target/hackaton-jump-0.0.1-SNAPSHOT.jar /app/app.jar

# Exponemos el puerto
EXPOSE 9494

# Ejecutamos la aplicacion
CMD ["java", "-jar", "/app/app.jar"]
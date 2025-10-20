# Etapa de construcción
FROM maven:3.8.5-openjdk-11-slim AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Etapa de ejecución
FROM openjdk:11-jdk-slim
WORKDIR /app

# Copiar el jar desde la etapa anterior (no desde el host)
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8190
CMD ["java", "-jar", "app.jar"]

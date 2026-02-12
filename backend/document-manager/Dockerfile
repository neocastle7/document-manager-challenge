# Estágio 1: Build
# Removemos o --platform para o Docker escolher o melhor para o seu PC agora
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app

# Copia tudo para o container
COPY . .

# Executa o build (gerando o .jar)
RUN mvn clean package -DskipTests

# Estágio 2: Runtime
FROM eclipse-temurin:21-jdk-jammy
WORKDIR /app

# Copia o jar do estágio anterior
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
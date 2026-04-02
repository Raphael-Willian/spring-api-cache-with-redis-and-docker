#Dependência java 21
FROM eclipse-temurin:21-jdk-jammy

#Diretório dentro no container
WORKDIR /app

#Copia do .jar
COPY target/*.jar app.jar

#Exposição da porta
EXPOSE 8080

#command run
ENTRYPOINT ["java", "-XX:+UseContainerSupport", "-jar", "app.jar"]

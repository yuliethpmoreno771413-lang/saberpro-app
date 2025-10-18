# Usa una imagen oficial de Java 17
FROM openjdk:17-jdk-slim

# Crea una carpeta de trabajo
WORKDIR /app

# Copia el pom y descarga dependencias
COPY pom.xml .
COPY mvnw .
COPY .mvn .mvn
RUN ./mvnw dependency:go-offline -B

# Copia el resto del proyecto
COPY . .

# Empaqueta la aplicación
RUN ./mvnw clean package -DskipTests

# Expone el puerto (Render usa variable PORT)
EXPOSE 8080

# Comando para ejecutar
CMD ["java", "-jar", "target/saberpro-0.0.1-SNAPSHOT.jar"]

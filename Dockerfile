# Usa una imagen oficial de Java 21
FROM openjdk:21-jdk-slim

# Crea la carpeta de trabajo
WORKDIR /app

# Copia los archivos necesarios para descargar dependencias
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Da permisos de ejecución al mvnw
RUN chmod +x mvnw

# Descarga dependencias sin compilar todo
RUN ./mvnw dependency:go-offline -B

# Copia el resto del proyecto
COPY . .

# Empaqueta la aplicación (sin correr tests)
RUN ./mvnw clean package -DskipTests

# Expone el puerto
EXPOSE 8080

# Ejecuta el jar (ajusta el nombre si tu .jar se llama distinto)
CMD ["java", "-jar", "target/saberpro-0.0.1-SNAPSHOT.jar"]

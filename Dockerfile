# Usa una imagen base de Java 17
FROM openjdk:17-jdk-slim

# Establece el directorio de trabajo
WORKDIR /app

# Copia los archivos de Maven Wrapper
COPY mvnw .
COPY .mvn .mvn

# 🔹 Da permisos de ejecución al archivo mvnw
RUN chmod +x mvnw

# Descarga las dependencias necesarias
RUN ./mvnw dependency:go-offline -B

# Copia el resto del proyecto
COPY . .

# Compila el proyecto
RUN ./mvnw package -DskipTests

# Expone el puerto en el que corre tu aplicación
EXPOSE 8080

# Ejecuta el archivo .jar generado
CMD ["java", "-jar", "target/saberpro-0.0.1-SNAPSHOT.jar"]


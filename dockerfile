# Usamos la imagen base de OpenJDK 17 con Alpine
FROM openjdk:17-jdk-alpine

# Establecemos el directorio de trabajo en /app
WORKDIR /app

# Copiamos los archivos necesarios para Maven
COPY .mvn/ .mvn
COPY mvnw pom.xml ./

# Copiamos el script de entrada
COPY entrypoint.sh ./

# Damos permisos de ejecución al script de entrada y descargamos las dependencias de Maven
RUN chmod +x entrypoint.sh
RUN ./mvnw dependency:go-offline

# Copiamos el código fuente de la aplicación
COPY src ./src

# Exponemos el puerto 8080
EXPOSE 8080

# Definimos el punto de entrada para nuestra aplicación
ENTRYPOINT ["./entrypoint.sh"]
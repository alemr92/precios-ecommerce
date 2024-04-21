FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY .mvn/ .mvn
COPY mvnw pom.xml ./
COPY entrypoint.sh ./

RUN chmod +x entrypoint.sh
RUN ./mvnw dependency:go-offline

COPY src ./src

EXPOSE 8080
ENTRYPOINT ["./entrypoint.sh"]

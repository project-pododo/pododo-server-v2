FROM openjdk:21-jdk-slim
COPY build/libs/pododo-server-0.0.1-SNAPSHOT.jar pododo-server.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "/pododo-server.jar"]
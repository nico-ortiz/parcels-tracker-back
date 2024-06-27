FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/goldeng-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app_commissions.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app_commissions.jar"]
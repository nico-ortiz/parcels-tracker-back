FROM eclipse-temurin:17.0.11_9-jdk
COPY --from=build target/goldeng-0.0.1-SNAPSHOT.jar app_commissions.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app_commissions.jar"]
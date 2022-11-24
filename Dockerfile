FROM openjdk:8

COPY build/libs/tour-leader-api-0.0.1-SNAPSHOT.jar /app/application.jar

CMD ["java", "-jar", "/app/application.jar"]
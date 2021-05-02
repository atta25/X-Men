FROM openjdk:8-jdk-alpine
WORKDIR /opt
COPY target/app.jar app.jar
COPY src/main/resources/*.properties /opt/
ENTRYPOINT ["java","-Dspring.profiles.active=prod",  "-jar", "app.jar"]

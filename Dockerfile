FROM openjdk:11
ARG JAR_FEIL=build/libs/*.jar
COPY ${JAR_FEIL} app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]

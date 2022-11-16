FROM --platform=linux/amd64 gradle:jdk17-alpine AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN ./gradlew clean build --no-daemon

FROM --platform=linux/amd64 openjdk:17-alpine

COPY --from=build /home/gradle/src/build/libs/*.jar /app/medical-clinic.jar
CMD java $JAVA_OPTS -jar /home/gradle/src/medical-clinic.jar

EXPOSE 8080

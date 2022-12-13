FROM --platform=linux/amd64 gradle:jdk17-alpine AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
#Use on Windows:
#RUN gradle clean build --no-daemon
#Use on MacOS/Linux:
RUN ./gradlew clean build --no-daemon


FROM --platform=linux/amd64 openjdk:17-alpine

#Use on Windows
#COPY --from=build /home/gradle/src/build/libs/medical-clinic-0.0.1-SNAPSHOT.jar /app/medical-clinic.jar
#Use on MacOS/Linux
COPY --from=build /home/gradle/src/build/libs/*.jar /app/medical-clinic.jar

CMD java $JAVA_OPTS -jar /app/medical-clinic.jar --spring.datasource.url=jdbc:postgresql://host.docker.internal:5432/gd-medical-clinic

EXPOSE 8080

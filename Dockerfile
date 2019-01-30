FROM openjdk:8 AS BUILD_IMAGE
ENV APP_HOME=/usr/app/
WORKDIR $APP_HOME
COPY build.gradle settings.gradle gradlew $APP_HOME
COPY gradle $APP_HOME/gradle

# download dependencies this will fail the build but it optimises layer usage
RUN ./gradlew build || return 0
COPY . .
RUN ./gradlew build

FROM openjdk:8-jre-alpine
ENV ARTIFACT_NAME=certification-pipeline-0.0.1-SNAPSHOT.jar
ENV APP_HOME=/usr/app/
WORKDIR $APP_HOME
COPY --from=BUILD_IMAGE $APP_HOME/build/libs/$ARTIFACT_NAME .
EXPOSE 8080
CMD ["java","-jar","certification-pipeline-0.0.1-SNAPSHOT.jar"]

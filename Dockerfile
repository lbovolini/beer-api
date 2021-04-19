# Build
FROM maven:3.8.1-openjdk-16-slim AS BUILD

ENV APP_FOLDER=~/beer-stock-api

WORKDIR ${APP_FOLDER}

COPY src src
COPY pom.xml pom.xml

RUN --mount=type=cache,target=/root/.m2 mvn -Dmaven.test.skip=true clean package

# Run
FROM openjdk:16-jdk-slim

ENV APP_NAME=beer-stock
ENV APP_FOLDER=~/beer-stock-api

WORKDIR ${APP_FOLDER}

COPY --from=BUILD  "${APP_FOLDER}/target/${APP_NAME}*.jar" ${APP_NAME}.jar

EXPOSE 8080

ENTRYPOINT java -jar ${APP_NAME}.jar
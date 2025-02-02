FROM eclipse-temurin:23-jdk-alpine AS builder

WORKDIR /app

COPY ../build.gradle settings.gradle gradlew /app/
COPY ../gradle /app/gradle/

RUN ./gradlew dependencies --no-daemon || true

COPY .. /app

RUN ./gradlew clean build -x test --no-daemon

FROM eclipse-temurin:23.0.2_7-jre-alpine-3.21

WORKDIR /app

RUN addgroup -S java && adduser -S java -G java

COPY --from=builder /app/build/libs/*.jar app.jar

ENV SPRING_PROFILES_ACTIVE=prod
ENV JAVA_OPTS="-XX:+UseContainerSupport"

# Log level configuration
ENV SPRING_DEV_TOOL=false
ENV SPRING_LOGGING_LEVEL_ROOT=info
ENV SPRING_LOGGING_LEVEL_ORG_SPRINGFRAMEWORK_WEB=info
ENV SPRING_LOGGING_LEVEL_ORG_HIBERNATE=warn
ENV SPRING_JPA_SHOW_SQL=false
ENV SPRING_JPA_PROPERTIES_HIBERNATE_FORMAT_SQL=false

EXPOSE 8080

USER java

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]

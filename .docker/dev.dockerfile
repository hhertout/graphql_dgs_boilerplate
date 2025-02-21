FROM eclipse-temurin:23.0.2_7-jdk-alpine

RUN apk add --no-cache inotify-tools

WORKDIR /app

COPY gradle gradle
COPY gradlew gradlew
COPY settings.gradle settings.gradle
COPY build.gradle build.gradle

RUN ./gradlew dependencies

COPY . .

RUN ./gradlew build -x test

COPY .docker/entrypoint.sh .
RUN chmod +x entrypoint.sh

EXPOSE 8080

ENV SPRING_PROFILES_ACTIVE=dev
ENV SPRING_DEVTOOLS_RESTART_ENABLED=true
ENV SPRING_PROFILES_ACTIVE=dev
ENV SPRING_DEVTOOLS_REMOTE_SECRET=devsecret

CMD ["sh", "entrypoint.sh"]

FROM eclipse-temurin:23-jdk

RUN apt-get update && apt-get install libinotifytools0 inotify-tools

WORKDIR /app

COPY build.gradle settings.gradle /app/

RUN ./gradlew dependencies || true

COPY .. /app

COPY .docker/entrypoint.sh .
RUN chmod +x entrypoint.sh

EXPOSE 8080

ENV SPRING_PROFILES_ACTIVE=dev
ENV SPRING_DEVTOOLS_RESTART_ENABLED=true
ENV SPRING_PROFILES_ACTIVE=dev
ENV SPRING_DEVTOOLS_REMOTE_SECRET=devsecret

CMD ["sh", "entrypoint.sh"]

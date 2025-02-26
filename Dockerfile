FROM amazoncorretto:21-alpine3.20 AS builder
RUN apk add --no-cache gradle binutils
WORKDIR /usr/src/app
COPY . .
RUN chmod +x gradlew
RUN ./gradlew build
RUN $JAVA_HOME/bin/jlink \
    --module-path $JAVA_HOME/jmods \
    --add-modules ALL-MODULE-PATH \
    --strip-debug \
    --no-man-pages \
    --no-header-files \
    --compress=2 \
    --output /jre

FROM alpine:3.20.2
ENV JAVA_HOME=/jre
ENV PATH="$JAVA_HOME/bin:$PATH"
COPY --from=builder /jre $JAVA_HOME
COPY --from=builder /usr/src/app/build/libs/*.jar /app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
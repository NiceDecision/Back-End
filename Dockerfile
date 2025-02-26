FROM amazoncorretto:21-alpine3.20 AS builder
RUN apk add --no-cache gradle binutils
RUN gradle build
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
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["sh", "-c", "java ${JAVA_OPTS1$} ${JAVA_OPTS2} ${JAVA_OPTS3} -jar /app.jar"]

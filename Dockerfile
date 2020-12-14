FROM openjdk:11 AS builder
WORKDIR target/dependency
ARG APPJAR=target/*.jar
COPY ${APPJAR} app.jar
RUN jar -xf ./app.jar

FROM openjdk:11
VOLUME /tmp
ARG DEPENDENCY=target/dependency
COPY --from=builder ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=builder ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=builder ${DEPENDENCY}/BOOT-INF/classes /app
ENTRYPOINT ["java","-cp","app:app/lib/*","com.asseco.ce.jtsr_digi.product_catalogue.OpenAPI2SpringBoot"]
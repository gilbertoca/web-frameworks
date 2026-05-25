FROM maven:3.9-eclipse-temurin-17-alpine AS builder
WORKDIR /app
COPY pom.xml .
COPY src ./src
# Mount the container's maven repo path to a persistent build cache
RUN --mount=type=cache,target=/root/.m2 mvn clean package

FROM tomcat:9.0-jre17
RUN rm -rf /usr/local/tomcat/webapps/*
COPY --from=builder /app/target/click-benchmark.war /usr/local/tomcat/webapps/ROOT.war
EXPOSE 8080
CMD ["catalina.sh", "run"]

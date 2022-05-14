FROM openjdk:17-jdk-alpine
COPY target/square-coding-test-0.0.1-SNAPSHOT.jar square-coding-test.jar
ENTRYPOINT ["java","-jar","/square-coding-test.jar"]

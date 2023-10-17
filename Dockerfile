FROM openjdk:17
ARG JAR_FILE=target/demotingeso-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} demotingeso-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar", "/demotingeso-0.0.1-SNAPSHOT.jar"]
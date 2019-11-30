FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD target/employee-service-0.0.1-SNAPSHOT.jar employee-service.jar
RUN sh -c 'touch /employee-service.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/employee-service.jar"]
EXPOSE 8082
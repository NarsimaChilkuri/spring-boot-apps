FROM java:8-jdk-alpine

COPY ./target/spring-jpa-hibernate-example-0.0.1-SNAPSHOT.jar /usr/app/app.jar

WORKDIR /usr/app

ENTRYPOINT ["java","-jar","app.jar"]

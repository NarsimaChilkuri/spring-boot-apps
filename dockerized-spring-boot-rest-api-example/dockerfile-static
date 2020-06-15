FROM java:8-jdk-alpine

COPY ./target/app-1.0-SNAPSHOT.jar /usr/app/app.jar

WORKDIR /usr/app

ENTRYPOINT ["java","-jar","app.jar"]

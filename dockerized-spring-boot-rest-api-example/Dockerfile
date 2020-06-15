FROM java:8-jdk-alpine

ENV MAVEN_VERSION 3.2.5

RUN apk update && apk add curl

RUN curl -sSL http://archive.apache.org/dist/maven/maven-3/$MAVEN_VERSION/binaries/apache-maven-$MAVEN_VERSION-bin.tar.gz | tar xzf - -C /usr/share \
  && mv /usr/share/apache-maven-$MAVEN_VERSION /usr/share/maven \
  && ln -s /usr/share/maven/bin/mvn /usr/bin/mvn

ENV MAVEN_HOME /usr/share/maven

WORKDIR /usr/src/app

COPY . .

RUN ["mvn", "clean", "install"]

EXPOSE 8080

ENTRYPOINT ["java","-jar","target/app-1.0-SNAPSHOT.jar"]

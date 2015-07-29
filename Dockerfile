FROM java:7u79-jdk

WORKDIR /opt

ADD ./target/*.jar ./
ADD ./bin/appB ./

RUN ls -la

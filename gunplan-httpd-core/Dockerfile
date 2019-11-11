FROM openjdk:11-jre

WORKDIR /

ADD  target/gunplan-httpj-1.1-SNAPSHOT-jar-with-dependencies.jar gunplan-httpj-1.1-SNAPSHOT-jar-with-dependencies.jar

EXPOSE 8822

ENTRYPOINT ["java","-jar","gunplan-httpj-1.1-SNAPSHOT-jar-with-dependencies.jar"]

FROM openjdk:18
WORKDIR /app
COPY ./target/playbit-0.0.1-SNAPSHOT.war /app
COPY . /app
COPY pom.xml /app
EXPOSE 8080
RUN [ "java", "-jar", "playbit-0.0.1-SNAPSHOT.war" ]

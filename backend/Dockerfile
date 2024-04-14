FROM openjdk:18
WORKDIR /app
COPY . /app
COPY pom.xml /app
EXPOSE 8080
CMD [ "./mvnw", "spring-boot:run" ]

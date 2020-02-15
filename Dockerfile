FROM openjdk:12
ADD target/docker-demo.jar docker-demo.jar
EXPOSE 8089
ENTRYPOINT ["java", "-jar", "docker-demo.jar"]
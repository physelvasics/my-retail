FROM adoptopenjdk/openjdk11:latest

RUN mkdir /opt/app
COPY target/product*.jar /opt/app/product-api.jar

CMD ["java", "-Denvironment=docker", "-jar", "/opt/app/product-api.jar"]
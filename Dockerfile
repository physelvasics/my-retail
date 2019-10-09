FROM adoptopenjdk/openjdk11:latest

RUN mkdir /opt/app
COPY target/product*.jar /opt/app/product-api.jar
COPY docker/apm/elastic-apm-agent.jar /opt/app/elastic-apm-agent.jar
COPY docker/apm/application-init.sh /opt/app/application-init.sh

CMD ["sh", "/opt/app/application-init.sh"]
# my-retail Product Detail Service
This repository was created part of Target case study.

## Technology Stack:
Java 11, Spring Boot, Spock, Cassandra, Opentracing, Groovy, Maven, Jmeter, Docker, Swagger2, AWS

## Getting Started
Follow the instructions to run the application on local machine using docker.

### Install docker:

```
For mac : https://docs.docker.com/docker-for-mac/install/
For Windows: https://docs.docker.com/docker-for-windows/install/
```
> Change the docker preference to allocate at least 4GB memory and 4 CPUs, this is required for elasticsearch container to run.


### Install maven:
```
https://maven.apache.org/install.html
```

### Install Openjdk11:
```
https://openjdk.java.net/install/
```

### Build and Run:

```
mvn clean install

docker build -t product-api .

cd docker

docker-compose build

docker-compose up -d
```
> If container creation failed, please rerun the last command from above. 

> **Note**: Integration test needs local cassandra container to be running, so run following to bring down the application container and then run integration test from source code.
>
> ```
>   docker-compose stop product-api
> ```


### Swagger
All api endpoints are documented using swagger.

[Local URL](http://localhost/swagger-ui.html)

[Hosted URL](http://99.79.62.181/swagger-ui.html)

### Elastic APM(Kibana):
This is part of Opentracing, we can monitor the request, response and system latency at each integration points.
> Note: Click on the APM link from the left pane. 

[Local URL](http://localhost:5601)

[Hosted URL](http://99.79.62.181:5601)
 
### Further Reading:
* [SpringBoot](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/) - Application Framework
* [Maven](https://maven.apache.org/developers/index.html) - Dependency & Build
* [Groovy](http://groovy-lang.org/documentation.html) - Unit Testing
* [Cassandra](http://cassandra.apache.org/doc/latest/architecture/index.html) - Datastore
* [Spock](http://spockframework.org/spock/docs/1.1/index.html) - Testing
* [Opentracing](https://opentracing.io/docs/overview/) - Distributed Logging
* [Elastic APM](https://www.elastic.co/products/apm) - APM Implementation



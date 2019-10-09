#!/usr/bin/env bash

java -javaagent:/opt/app/elastic-apm-agent.jar \
     -Delastic.apm.service_name=my-retail \
     -Delastic.apm.server_url=http://apmserver:8200 \
     -Delastic.apm.secret_token= \
     -Delastic.apm.application_packages=com.myretail.product \
     -Denvironment=docker \
     -jar /opt/app/product-api.jar
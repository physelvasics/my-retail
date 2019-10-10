#!/usr/bin/env bash

mvn clean install

docker-compose stop product-api

docker build -t product-api .

docker-compose build

docker-compose up
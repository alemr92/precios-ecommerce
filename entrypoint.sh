#!/bin/sh

if [ "$1" = "test" ]; then
  ./mvnw test
else
  ./mvnw spring-boot:run
fi
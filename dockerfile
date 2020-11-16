#FROM is the base image for which we will run our application
#
# Build stage
FROM maven:3.6.0-jdk-11-slim AS build
# Copy files and directories from the application
COPY src F:/David/Develop2/testAPI/src
COPY pom.xml F:/David/Develop2/testAPI/pom.xml
RUN mvn -f F:/David/Develop2/testAPI/pom.xml clean package

# Package stage
FROM openjdk:8
EXPOSE 8080
ADD target/testAPI.jar testAPI.jar
ENTRYPOINT ["java","-jar","/testAPI.jar"]

MAINTAINER David
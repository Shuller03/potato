#FROM openjdk:17
#WORKDIR /app
#COPY target/potato-0.0.1-SNAPSHOT.jar app.jar
#EXPOSE 8090
#
## Set any environment variables required by your application
##ENV TZ=UTC
##ARG APP_VERSION
##ENV APP_VERSION=${APP_VERSION}
#
#CMD ["java", "-jar", "app.jar"]
#
#
##FROM maven:latest as BUILD
##COPY src /home/app/src
##COPY pom.xml /home/app
##RUN mvn -f /home/app/pom.xml clean package
##
##FROM openjdk:17
##COPY --from=build /home/app/target/potato-0.0.1-SNAPSHOT.jar /usr/local/lib/potato.jar
##EXPOSE 8080
##ENTRYPOINT ["java","-jar","/usr/local/lib/potato.jar"]
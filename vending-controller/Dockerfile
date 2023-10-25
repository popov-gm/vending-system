FROM openjdk:17-alpine
EXPOSE 30003
ARG JAR_FILE=build/libs/vending-controller-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
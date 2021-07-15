FROM openjdk8-jdk-alpine
expose 8080
ARG JAR_FILE=PhoneNumberValidator.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT [java,-jar,app.jar]
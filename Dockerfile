FROM openjdk:10-jre-slim

WORKDIR /app
COPY ./target/tec-pay-account-ws-0.0.1/SNAPSHOT.jar /app

EXPOSE 8080

CMD ["java", "-jar", "tec-pay-account-ws-0.0.1/SNAPSHOT.jar"]

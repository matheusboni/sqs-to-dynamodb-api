FROM openjdk:11
ADD target/sqs-to-dynamodb-api.jar sqs-to-dynamodb-api.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/sqs-to-dynamodb-api.jar"]
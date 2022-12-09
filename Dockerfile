FROM openjdk:17-oracle
ADD target/ms-credit-0.0.1-SNAPSHOT.jar ms-credit.jar
ENTRYPOINT ["java","-jar","ms-credit.jar"]
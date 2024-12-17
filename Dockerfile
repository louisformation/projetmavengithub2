FROM maven
COPY target/calculatorD-0.0.1-SNAPSHOT.jar app.jar
CMD ["java","-jar","app.jar"]

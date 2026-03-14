FROM eclipse-temurin:17
COPY target/student-manager-1.0.jar app.jar
EXPOSE 9090
ENTRYPOINT ["java","-jar","/app.jar"]

FROM maven:3.5-jdk-8 AS build  
WORKDIR /app
COPY . /app
RUN mvn -f /app/pom.xml clean package

FROM gcr.io/distroless/java  
COPY --from=build /app/target/Personal_Project_Library-1.0.jar /app/Personal_Project_Library-1.0.jar  
EXPOSE 8080  
ENTRYPOINT ["java","-jar","/app/Personal_Project_Library-1.0.jar"]  
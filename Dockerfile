FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /workspace/app
COPY . .
RUN mvn clean package -DskipTests -B

FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /workspace/app/target/blog-system-1.0.0.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"] 
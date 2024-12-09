FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY .mvn/ .mvn/
COPY mvnw pom.xml ./
COPY src ./src

RUN chmod +x mvnw
RUN ./mvnw dependency:go-offline

RUN ./mvnw clean package -DskipTests

ENV PORT=8080
EXPOSE ${PORT}
CMD ["sh", "-c", "java -jar target/blog-system-1.0.0.jar --server.port=${PORT}"] 
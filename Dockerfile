FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY gradlew .
COPY gradle/ gradle/
COPY build.gradle .
COPY settings.gradle .

RUN chmod +x gradlew

COPY . .

RUN ./gradlew build -x test

CMD ["java", "-jar", "build/libs/homepage-0.0.1-SNAPSHOT.jar"]

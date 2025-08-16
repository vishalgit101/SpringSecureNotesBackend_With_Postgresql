FROM eclipse-temurin:21-jdk

LABEL maintainer="vishalgit101@gmail.com"

WORKDIR /app

COPY target/SecureNotesPracticeProject-0.0.1-SNAPSHOT.jar SecureNotesPracticeProject.jar

ENTRYPOINT ["java", "-jar", "SecureNotesPracticeProject.jar"]


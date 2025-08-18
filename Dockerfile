#FROM eclipse-temurin:21-jdk

#LABEL maintainer="vishalgit101@gmail.com"

#WORKDIR /app

#COPY target/SecureNotesPracticeProject-0.0.1-SNAPSHOT.jar SecureNotesPracticeProject.jar

#ENTRYPOINT ["java", "-jar", "SecureNotesPracticeProject.jar"]

#----------------800MB Image Size was being created with above file-------------------
	
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

COPY target/SecureNotesPracticeProject-0.0.1-SNAPSHOT.jar SpringApp.jar

ENTRYPOINT ["java", "-jar", "SpringApp.jar"]

# This should create small build, but won't have proper debug logs with this alpine version

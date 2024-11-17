FROM openjdk:21-slim
WORKDIR /app
COPY poisePMS.jar /app/poisePMS.jar
ENV DB_URL="jdbc:mysql://mysql:3306/poisepms"
ENV DB_USER="root"
ENV DB_PASSWORD="Jenice@18"
CMD ["java", "-jar", "poisePMS.jar"]
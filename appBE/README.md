1. Prerequisite
   - Install JKD 21+
   - Install Maven 4.0+
   - Install IntelliJ IDE
   - Install Docker
   - Install MySQL
2. Technicals
   - Java 17
   - Maven 4.0+
   - Spring Boot 3.3.0+
   - Spring Data Validation
   - Spring Data JPA
   - MySQL
   - Docker
   - Lombok
3. Build & Run Application

   ## Run with mvnw at folder appBE

   - mvn clean install -P dev|test|prod
   - ./mvnw spring-boot:run

   ## Run with docker

   - mvn clean install -P dev|test|prod
   - docker build -t your_image_name:latest .
   - docker run -d your_image_name:latest

4. Test
   4.1. Check health with cURL
   - curl --location 'http://localhost:8080/actuator/health'

## App Backend

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

```bash
    mvn clean install -P dev|test|prod
    ./mvnw spring-boot:run
```

```bash
    crontab -e
```

## Run with docker

```bash
    mvn clean install -P dev|test|prod
    docker build -t your_image_name:latest .
    docker run - your_image_name:latest
```

4. Test
   4.1. Check health with cURL

   - curl --location 'http://localhost:8080/actuator/health'

## App Frontend

1. Prerequisite

   - Install NodeJS 21
   - Install visual studio code
   - Install Docker

1. Build & Run Application

## Run folder appFE

```bash
   npm install pnpm -g
   pnpm install
   pnpm dev
```

## Run with docker

```bash
    docker build -t your_image_name:latest .
    docker run - your_image_name:latest
```

## Deploy

## Run docker width command: docker compose -p thuanhm up -d

## Automated with crontab:

```bash
    crontab -e
```

```bash
    crontab renew ssl, reload nginx, backup database
```

```bash
    0 0 1 * * docker compose -f /skinlab-by-tuyen/docker-compose.yml -p thuanhm up certbot
    0 0 1 * * docker compose -f /skinlab-by-tuyen/docker-compose.yml -p thuanhm restart nginx
    0 0 * * * sudo docker compose -p thuanhm exec mysqldb /usr/bin/mysqldump -u root --password=121212 blogapp > ~/backupMySQL/mysql_backup.sql
```

## remove container and images docker:

```bash
    docker stop $(docker ps -aq)
    docker system prune -af
```

<!--Deploy-->

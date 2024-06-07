## Securing Your Spring Boot App with JWT Authentication
This project is a simple example of how to protect endpoints with spring security JWT

### Requirements
- PostgreSQL database

### How to run
1. `export SPRING_DATASOURCE_URL=...` The format should be `url: jdbc:postgresql://host:port/db_name?sslmode=require&application_name=spring-security-demo&user=...&password=...`. You can easily create PostgreSQL database for free [here](https://rapidapp.io)
2. `export JWT_SECRET=thisshouldbeatleast32chars`
3. `./mvnw spring-boot:run`
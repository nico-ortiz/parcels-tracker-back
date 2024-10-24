# Parcels tracker Backend 📦
This repository contains an API Rest project built using Java Spring. This project is Backend of a Parcels Tracker app.

# Description
This API brings endpoints to consume data about Parcels, Customers, Packages and Receivers (basic CRUD and others endpoints). These are project models that represent information manipulated in the app. \
You can test the API endpoints directly from the Swagger documentation &rarr; [Swagger Documentation](https://commissions-tracker-api.onrender.com/swagger-ui/index.html) \
Postman documentation &rarr; [Postman Documentation](https://documenter.getpostman.com/view/17467236/2sAY4rF56c)

# Technologies
I used Spring framework to build the API and PostgreSQL to persist the information in the database.

Technologies employed:
- Java 17
- Spring Data JPA
- Spring Security (to implement)
- PostgreSQL
- Maven
- Swagger

# Installation
1. Clone the repository:
```bash
$ git clone https://github.com/nico-ortiz/commissions-tracker-back.git
```
2. Install dependencies with Maven

## Usage

1. Start the application with Maven
2. The API will be accessible at http://localhost:8080

## Docker

You can run this project with Docker by running the following command:

```bash 
$ docker compose up
``` 

And then point your browser to http://localhost:8080

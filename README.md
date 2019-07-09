# **FoodForHunger**


## *Ideas and Overview*

### Why this Project?
*Everybody loves food, especially when it is free! And nobody likes to see food lying on the table and end up in the trash by end of day... So I want to create something for people to share and get free food!*

### What is used to create the project?
*This is a Java8 backend project developed in Sping Framework. Things used are: Spring Boot, Spring Data, Hibernate, Spring RESTful web services, Postman, Maven, PostgresSQL, Docker, Amazon SQS and Amazon S3.*

*Business Roles:*

1. Object: User, Food, Image, Building, Area

2. Relationships:
	2.1 One user can post many food records.
	2.2 One food posted can have several images.
	2.3 One area could have many buildings.
	2.4 Food record is mapped to one building and its area.
	2.5 One user can choose to not subscribe or subscribe to any buildings/areas as desired. 

*Project Approach:*

1. Created User, Building, Food, Image Domain
2. Used Hibernate to do the database schema migration
3. Used JDBC to connect project with Postgres
4. Configured Spring Secutiry for Authentication
5. Created repository, service and ran unit test
6. Created mock testing for AWS S3 Storage service
7. Created Controllers and Restful APIs
8. Integrated third-party application AWS SQS and ran Mock test
9. Used Postman to test back end results
10. Package project into a Docker image

### Local environment configuration

```
docker pull postgres

docker run --name ${PostgresContainerName} -e POSTGRES_USER=${username} -e POSTGRES_PASSWORD=${password} -e POSTGRES_DB=${databaseName} -p ${hostport}:${containerport} -d postgres
```

### Environment property configuration

```
location:./src/main/resources/META-INF/env
   
Template:
database.driverName=${driverName}
database.url=${url}
database.port=${port}
database.name=${name}
database.username=${username}
database.password=${password}
   
mvn compile -Dspring.profiles.active=${env}
```

### Flyway migration

```$xslt
mvn compile flyway:migrate -P unit -Ddb_username=${username} -Ddb_url=localhost:${containerport}/${databasename} -Ddb_password=${password} 
```

### Testing

```mvn clean compile install -DskipTests=true```
* Tests are done using JUnit and Mockito. Tests are run using the command:
```$xslt
mvn compile test -Dspring.profiles.active=${env} -P ${env}
mvn compile test -Dspring.profiles.active=${unit} -Daws.region=${region} -Ddb_url=${localhost:5432/pigge_unit} -Ddb.username=${username} -Ddb.password=${password} 
```
```$xslt
location:./src/main/resources/META-INF/env

Template:
database.driverName=${driverName}
database.url=${url}
database.port=${port}
database.name=${name}
database.username=${username}
database.password=${password}
```

### Create war package file

```$xslt
mvn compile package -P dev -DskipTests=true
```


	








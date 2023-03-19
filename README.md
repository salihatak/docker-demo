# Docker Demo Application

The Docker Demo Application is a project designed to demonstrate the use of Spring, Docker, Lombok, and Swagger.

## Prerequisites
Before you can use the Docker Demo Application, you need to have the following:

- Docker installed on your system
- MySQL Server running on Docker
- Java Development Kit (JDK) installed on your system

## Installation

To use the Docker Demo Application, just run command below
```
docker-compose up
```

Or follow the steps below:

1. Clone the project from the repository:

```
git clone https://github.com/salihatak/docker-demo.git
```
2. Build the Docker image from the Dockerfile:
```
docker build -t docker-demo .
```
3. Run the MySQL Server on Docker:
```
docker run --name mysql-standalone -e MYSQL_ROOT_PASSWORD=password -e MYSQL_DATABASE=test -e MYSQL_USER=sa -e MYSQL_PASSWORD=password -d mysql/mysql-server
```

4. Run the Spring Application on Docker:
```
docker run -d -p 8089:8089 --name docker-demo --link mysql-standalone:mysql/mysql-server docker-demo
```

5. To Login MySQL running on docker:
```
docker exec -it mysql-standalone mysql -uroot -p
```

## Usage
Once the Docker Demo Application is up and running, you can access the Swagger UI at:

http://localhost:8089/swagger-ui.html


From the Swagger UI, you can test the various endpoints available in the application.

## Contributing
Please see [CONTRIBUTING.md](CONTRIBUTING.md) for details on how to contribute to this project.

## License
This project is licensed under the [MIT License](LICENSE).

# docker-demo


Command to run MySQL Server on docker :

```
docker run --name mysql-standalone -e MYSQL_ROOT_PASSWORD=password -e MYSQL_DATABASE=test -e MYSQL_USER=sa -e MYSQL_PASSWORD=password -d mysql/mysql-server
```

Command to run Spring Application on docker :

```
docker run -d -p 8089:8089 --name docker-demo --link mysql-standalone:mysql/mysql-server docker-demo
```

To Login MySQL running on docker

```
docker exec -it mysql-standalone mysql -uroot -p
```


# javpet


# Build
```shell script
mvn clean install
```

# Run
```shell script
java -Dservice.conf=./conf/development.yaml
-Dlog4j.configurationFile=./conf/log4j.xml
-Dredis.conf=./conf/redis.yaml
-cp *.jar vn.zalopay.ducnm8.Runner
```

```shell script
# Redis docker
docker run --name some-redis -d redis --expose 127.0.0.1:6379:6379

# Mysql docker
docker run -p 127.0.0.1:3306:3306/tcp --name mysql-a -e MYSQL_ROOT_PASSWORD=my-secret-pw -d mysql:5.7 
# init data
docker exec -i mysql-a sh -c 'exec mysql -uroot -p"my-secret-pw"' < /some/path/on/your/host/example.sql
```
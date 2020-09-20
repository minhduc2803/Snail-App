# Snail Project

## 1. Tổng quan

*Snail Project* là một training project thuộc chương trình ZaloPay Fresher 2020 sử dụng React JS, Vert.x để xây dụng một ứng dụng chat real-time đơn giản bao gồm các chức năng cơ bản:

- Đặng nhập / Đăng xuất (có sử dụng JWT)
- Đăng ký tài khoản
- Xem danh sách tất cả người dùng trong hệ thống
- Chat 1 - 1

Các công nghệ sử dụng:

- `React`, `Redux` cho client
- `Java Vert.x` xây dựng API cho server
- `WebSocket` gửi nhận tin nhắn real time
- Database dùng `MySQL`, cache dùng `Redis`

## 2. Hướng dẫn chạy

- Client chạy trên port 3000. Có dùng `yarn` để build
  
```shell script
    yarn install
 ```
  và khởi động

```shell script
    yarn start
```

- Vert.x server có API chạy trên port 8055 và WebSocket chạy trên port 9009. Dùng `maven` để build và run.

- Build server
  
```shell script
mvn clean install
```

- Run server
  
```shell script
java -Dservice.conf=./conf/development.yaml
-Dlog4j.configurationFile=./conf/log4j.xml
-Dredis.conf=./conf/redis.yaml
-cp *.jar bla.nah.example.Runner

```
## 3. Demo

### 3.1 Login

### 3.2 Register

### 3.3 View User List

### 3.4 Chat 1 - 1

## 4. Refernces

- [Sequence diagrams](docs/Sequence-Diagram.md)
- [Database design](docs/Database-Design.md)
- [Redis cache Specifications](docs/Redis-Cache-Specifications.md)
- [API and Websocket Specifications](https://app.swaggerhub.com/apis/minhduc2803/Snail-Project/1.0.0)
# spring-boot-demo

一个只有三个接口的 Spring Boot 示例服务，用来演示最小可用的 REST 项目结构。

- Java 17 / Spring Boot 3.3.5 / Maven
- 端口 `8080`（见 `src/main/resources/application.yml`）

## 接口

| 方法 | 路径 | 说明 |
|---|---|---|
| GET | `/api/hello?name=xxx` | 返回问候语；`name` 缺省为 `World` |
| GET | `/api/time` | 返回服务器当前时间（ISO-8601、毫秒时间戳、时区） |
| POST | `/api/echo` | 原样回显请求体，并附带内容类型与长度 |

### 示例

```bash
curl "http://localhost:8080/api/hello?name=Sun"
# {"message":"Hello, Sun!","service":"spring-boot-demo","timestamp":"2026-11-17T08:30:00Z"}

curl "http://localhost:8080/api/time"
# {"iso":"2026-11-17T08:30:00.123Z","epochMillis":1763368200123,"zone":"Asia/Shanghai"}

curl -X POST "http://localhost:8080/api/echo" \
     -H "Content-Type: application/json" \
     -d '{"ping":"pong"}'
# {"received":"{\"ping\":\"pong\"}","contentType":"application/json","length":13,"timestamp":"..."}
```

## 运行

```bash
mvn spring-boot:run
```

或先打包再运行：

```bash
mvn clean package
java -jar target/spring-boot-demo-0.0.1-SNAPSHOT.jar
```

## 测试

```bash
mvn test
```

覆盖三个接口的 `MockMvc` 测试在 `src/test/java/com/sunhaochen/demo/api/DemoControllerTest.java`。

## 目录结构

```
spring-boot-demo
├─ pom.xml
└─ src
   ├─ main
   │  ├─ java/com/sunhaochen/demo
   │  │  ├─ SpringBootDemoApplication.java
   │  │  └─ api/DemoController.java
   │  └─ resources/application.yml
   └─ test/java/com/sunhaochen/demo/api/DemoControllerTest.java
```

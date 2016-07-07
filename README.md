#spring-boot-start-dubbo

## 如何使用

### 1. clone 代码

```
git clone git@github.com:teaey/spring-boot-starter-dubbo.git
```

### 2. 编译安装

```
cd spring-boot-starter-dubbo
mvn clean install
```


### 3. 修改maven配置文件(可以参考样例[spring-boot-starter-dubbo-sample](https://github.com/teaey/spring-boot-starter-dubbo-sample))

* 在Spring Boot项目的pom.xml增加parent:
```
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>1.3.6.RELEASE</version>
</parent>
 ```

* 在Spring Boot项目的pom.xml中添加以下依赖:
```
 <dependency>
     <groupId>io.dubbo.springboot</groupId>
     <artifactId>spring-boot-starter-dubbo</artifactId>
     <version>1.0.0-SNAPSHOT</version>
 </dependency>
 ```

 * maven插件用于打包成可执行的uber-jar文件,添加以下插件(这里一定要加载需要打包成jar的mudule的pom中)
 ```
<plugin>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-maven-plugin</artifactId>
    <version>1.3.6.RELEASE</version>
</plugin>
  ```

### 4. 发布服务

在application.properties添加Dubbo的版本信息和客户端超时信息,如下:
```
spring.dubbo.application.name=provider
spring.dubbo.registry.address=zookeeper://192.168.99.100:32770
spring.dubbo.protocol.name=dubbo
spring.dubbo.protocol.port=20880
spring.dubbo.scan=cn.teaey.sprintboot.test
```


在Spring Application的application.properties中添加spring.dubbo.scan即可支持Dubbo服务发布,其中scan表示要扫描的package目录
* spring boot启动
```
@SpringBootApplication
public class Server {

    public static void main(String[] args) {
        SpringApplication.run(Server.class, args);
    }
```
* 编写你的Dubbo服务,只需要添加要发布的服务实现上添加 @Service ,如下
```
@Service(version = "1.0.0")
public class ServiceImpl implements ServiceA {

    @Override
    public String test() {
        return "hello";
    }
}

```

### 5. 消费Dubbo服务
* 在application.properties添加Dubbo的版本信息和客户端超时信息,如下:
```
spring.dubbo.application.name=consumer
spring.dubbo.registry.address=zookeeper://192.168.99.100:32770
spring.dubbo.scan=cn.teaey.sprintboot.test
```
在Spring Application的application.properties中添加spring.dubbo.scan即可支持Dubbo服务发布,其中scan表示要扫描的package目录

* spring boot启动
```
@SpringBootApplication
public class Client {
    public static void main(String[] args) {
        SpringApplication.run(Client.class, args);
    }
}
```
* 引用Dubbo服务,只需要添加要发布的服务实现上添加 @Reference ,如下:
```
@Component
public class BarAction {

    @Reference(version = "1.0.0")
    private ServiceA fooService;
}
```

# spring-boot-starter-dubbo


spring-boot-start-dubbo，让你可以使用spring-boot的方式开发dubbo程序。使dubbo开发变得如此简单。

让你可以使用`spring-boot`的方式开发`dubbo`程序。使`dubbo`开发变得如此简单。

## 如何使用

### 1. `clone`代码（可选，已经发布到中央仓库，可以直接依赖[中央仓库的稳定版本](http://search.maven.org/#search%7Cgav%7C1%7Cg%3A%22io.dubbo.springboot%22%20AND%20a%3A%22spring-boot-starter-dubbo%22)）

```sh
git clone git@github.com:teaey/spring-boot-starter-dubbo.git
```

### 2. 编译安装（可选）

```sh
cd spring-boot-starter-dubbo
mvn clean install
```

### 3. 修改`maven`配置文件（可以参考样例[`spring-boot-starter-dubbo-sample`](https://github.com/teaey/spring-boot-starter-dubbo-sample)）

* 在`spring boot`项目的`pom.xml`增加`parent`:

```xml
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>1.3.6.RELEASE</version>
</parent>
 ```

* 在`spring boot`项目的`pom.xml`中添加以下依赖：

根据实际情况依赖最新版本

```xml
 <dependency>
    <groupId>io.dubbo.springboot</groupId>
    <artifactId>spring-boot-starter-dubbo</artifactId>
    <version>1.0.0</version>
 </dependency>
 ```

* `maven`插件用于打包成可执行的`uber-jar`文件，添加以下插件(这里一定要加载需要打包成`jar`的`mudule`的`pom`中)

```xml
<plugin>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-maven-plugin</artifactId>
    <version>1.3.6.RELEASE</version>
</plugin>
```

### 4. 发布服务

服务接口：

```java
package cn.teaey.sprintboot.test;

public interface EchoService {
    String echo(String str);
}

```


在`application.properties`添加`Dubbo`的版本信息和客户端超时信息，如下：

```properties
spring.dubbo.application.name=provider
spring.dubbo.registry.address=zookeeper://192.168.99.100:32770
spring.dubbo.protocol.name=dubbo
spring.dubbo.protocol.port=20880
spring.dubbo.scan=cn.teaey.sprintboot.test
```


在`Spring Application`的`application.properties`中添加`spring.dubbo.scan`即可支持`Dubbo`服务发布，其中`scan`表示要扫描的`package`目录。

* `spring boot`启动

```java
package cn.teaey.sprintboot.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Server {
    public static void main(String[] args) {
        SpringApplication.run(Server.class, args);
    }
}

```

* 编写你的`Dubbo`服务，只需要添加要发布的服务实现上添加`@Service`，如下：

```java
package cn.teaey.sprintboot.test;

import com.alibaba.dubbo.config.annotation.Service;

@Service(version = "1.0.0")
public class EchoServerImpl implements EchoService {

    public String echo(String str) {
        System.out.println(str);
        return str;
    }
}

```

### 5. 消费`Dubbo`服务

* 在`application.properties`添加`Dubbo`的版本信息和客户端超时信息，如下：

```properties
spring.dubbo.application.name=consumer
spring.dubbo.registry.address=zookeeper://192.168.99.100:32770
spring.dubbo.scan=cn.teaey.sprintboot.test
```

在`Spring Application`的`application.properties`中添加`spring.dubbo.scan`即可支持`Dubbo`服务发布，其中`scan`表示要扫描的`package`目录。

* `spring boot`启动

```java
package cn.teaey.sprintboot.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Client {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(Client.class, args);
        AbcService bean = run.getBean(AbcService.class);
        System.out.println(bean.echoService.echo("abccc"));
    }
}

```

* 引用`Dubbo`服务，只需要添加要发布的服务实现上添加`@Reference`，如下：

```java
package cn.teaey.sprintboot.test;

import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Component;

@Component
public class AbcService {
    @Reference(version = "1.0.0")
    public EchoService echoService;
}
```

### 6. `monitor`监控中心
* 在`application.properties`添加`monitor`监控中心配置(服务端和消费端相同)，如下：

```properties
spring.dubbo.monitor.protocol=registry
```

### 7. 打包

- 可以直接执行`Server`或者`Client`启动
- 可以通过`mvn clean package`打包成可执行的`uber-jar`文件

## Rpamis-chain

🌱Rpamis-chain项目是一款轻量、高扩展性的责任链模式框架，内置多种责任链形态及执行策略

<p align="center">
  <a href="https://central.sonatype.com/artifact/com.rpamis/rpamis-chain/1.0.0">
    <img alt="maven" src="https://img.shields.io/maven-central/v/com.rpamis/rpamis-chain?style=flat-square">
  </a>

  <a href="https://www.apache.org/licenses/LICENSE-2.0">
    <img alt="code style" src="https://img.shields.io/badge/license-Apache%202-4EB1BA.svg?style=flat-square">
  </a>

  <a href="https://codecov.io/gh/benym/rpamis-chain" > 
    <img alt="codecov" src="https://img.shields.io/codecov/c/gh/rpamis/rpamis-chain?color=%23&style=flat-square"/> 
  </a>
</p>

-------------------------------------------------------------------------------

[**中文**](README-CN.md)|[**English**](README.md)

-------------------------------------------------------------------------------

### 快速开始

- 引入依赖

```xml
<dependency>
    <groupId>com.rpamis</groupId>
    <artifactId>rpamis-chain</artifactId>
    <version>1.0.0</version>
</dependency>
```

- 继承`AbstractChainPipeline`创建ChainPipeline

```java
// 处理List<User>类型数据
public class UserChainPipline extends AbstractChainPipeline<List<User>> {
    // 注入一些必要信息，或其他操作
}
```

- 实现`ChainHandler`创建ChainHandler

以下以创建3个处理类为例

```java
public class ValidateHandler implements ChainHandler<List<User>> {

    @Override
    public boolean process(List<User> handlerData) {
        return handlerData.stream().anyMatch(user -> {
            if (user.getName() == null || user.getPwd() == null) {
                System.out.println("用户名或密码为空");
                return false;
            }
            System.out.println("用户名或密码校验通过");
            return true;
        });
    }
}
```

```java
public class LoginHandler implements ChainHandler<List<User>> {

    @Override
    public boolean process(List<User> handlerData) {
        return handlerData.stream().anyMatch(user -> {
            if ("test".equals(user.getName()) && "123".equals(user.getPwd())) {
                user.setRole("admin");
            } else {
                System.out.println("用户校验失败");
                return false;
            }
            System.out.println("用户校验通过");
            return true;
        });
    }
}
```

```java
public class AuthHandler implements ChainHandler<List<User>> {

    @Override
    public boolean process(List<User> handlerData) {
        return handlerData.stream().anyMatch(user -> {
            if (!"admin".equals(user.getRole())) {
                System.out.println("超管校验失败");
                throw new RuntimeException("失败");
            }
            System.out.println("超管校验成功");
            return true;
        });
    }
}
```

- 构建责任链，并启动，获取处理结果

```java
List<User> list = new ArrayList<>();
User user = new User("test", "1232321");
list.add(user);
ChainPipeline<List<User>> chain = new UserChainPipline()
    .addHandler(new ValidateHandler())
    .addHandler(new LoginHandler())
    .addHandler(new AuthHandler())
    .strategy(new FullExecutionStrategy<>());
try {
    CompleteChainResult result = chain.start(list);
    // 判断整个链执行结果，默认有一个失败则判断为失败
    if (result.isAllow()) {
        System.out.println("责任链最终执行结果为" + result.isAllow());
    }
    // 根据处理类class获取单个处理类执行结果
    boolean validateHandlerResult = result.get(ValidateHandler.class);
} catch (ChainException e) {
    e.printStackTrace();
}
```

`CompleteChainResult`

- `isAllow`：获取整个链的执行结果，有一个失败则判断为失败，返回`boolean`
- `get(Class cls)`：根据`Handler`类`class`获取某个`Handler`处理结果，返回`boolean`

#### 内置策略

- `FullExecutionStrategy`： 全执行策略，责任链默认策略，无论某个`Handler`是否成功，都会执行完所有链上的处理
- `FastReturnStrategy`：快速返回策略，当`Handler`中有一个成功就立即返回结果，后续`Handler`不再执行
- `FastFailedStrategy`：快速失败策略，当`Handler`中有一个失败就立即返回结果，后续`Handler`不再执行

#### 策略扩展

你可以通过如下两步拓展责任链处理策略

- 实现`ChainStrategy`接口

```java
public class CustomStrategy<T> implements ChainStrategy<T>{

    @Override
    public ChainResult init(Class<?> handlerClass, boolean processResult) {
        return ChainStrategy.super.init(handlerClass, processResult);
    }

    @Override
    public void doStrategy(T handlerData, ChainPipeline<T> chain, ChainResult chainResult) throws IOException, ChainException {
        // do your custom chainstrategy
    }
}
```

`init`方法：提供了默认的处理结果包装

`doStrategy`方法：用于编写自己的责任链处理策略，`handlerData`为责任链处理的数据，`chain`为责任链上下文，`chainResult`为单次`Handler`处理的结果

- 构建责任链时使用该实现

```java
ChainPipeline<List<User>> chain = new UserChainPipline()
                .addHandler(new ValidateHandler())
                .addHandler(new LoginHandler())
                .addHandler(new AuthHandler())
                .strategy(new CustomStrategy<>());
```

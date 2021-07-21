# Spring-boot-with-inMemory-ActiveMQ

## Introduction 
You'll learn how to create a Standalone ActiveMQ Demo application in spring boot using the Producer-Consumer model.


## Dependencies
All the dependencies are needed should be in pom.xml then it resolves all internal dependencies automatically.

Main dependencies are defined below.

1) spring-boot-starter-activemq
2) spring-boot-starter-web
```javascript
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-activemq</artifactId>
</dependency>

<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```

## Configuration
Basic Producer Comsumer Model
```javascript
@EnableJms
@Configuration
public class ActiveMQConfig {

    @Bean
    public Queue queue() { return new ActiveMQQueue("inmemory.queue"); }

}
```

## Properties
add active mq properties and server port
```javascript
spring.activemq.in-memory=true
spring.activemq.pool.enabled=false
server.port=9090
```

## Producer
Create Producer for publishing the message
```javascript
@RestController
@RequestMapping(path = "/rest/publish")
public class Producer {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private Queue queue;

    @GetMapping(path = "/{message}")
    public String publish(@PathVariable("message") final String message)
    {
        jmsTemplate.convertAndSend(queue,message);
        return "Message Published successfully";
    }
}
```
## Consumer
create consumer for consume the message
```javascript
@Component
@Slf4j
public class Consumer {
    @JmsListener(destination = "inmemory.queue")
    public void listener(String message){
        log.info("Received Message : "+message + " ✔");
    }
}
```

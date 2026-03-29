# jda-spring-template

A minimal Spring Boot template for JDA Discord bots with MongoDB (long-term storage) and Redis (real-time cache).

## Stack

| Layer | Technology |
|---|---|
| Bot framework | JDA 5 |
| Application framework | Spring Boot 3.3 |
| Long-term storage | MongoDB (Spring Data) |
| Real-time cache | Redis (Lettuce / Spring Data) |
| Build | Gradle KTS |
| Boilerplate reduction | Lombok |
| Java version | 21 |

## Project structure

```
src/main/java/dev/mzcy/bot/
├── BotApplication.java          # Entry point
├── config/
│   ├── BotProperties.java       # @ConfigurationProperties for bot.*
│   ├── JdaConfig.java           # Builds the JDA bean; auto-registers all ListenerAdapter beans
│   └── RedisConfig.java         # RedisTemplate with Jackson + JSR-310 serialization
└── listener/
    └── ReadyListener.java       # Fires on ReadyEvent — add your own here
```

## Getting started

1. **Clone**
   ```bash
   git clone https://github.com/mzcy/jda-spring-template
   cd jda-spring-template
   ```

2. **Configure** — set environment variables or edit `application.yml`:
   ```
   BOT_TOKEN=your-discord-bot-token
   MONGO_URI=mongodb://localhost:27017/jda-template
   REDIS_HOST=localhost
   ```

3. **Run**
   ```bash
   ./gradlew bootRun
   ```

## Adding a listener

Create a class that extends `ListenerAdapter` and annotate it with `@Component`. Spring injects it automatically into JDA.

```java
@Slf4j
@Component
@RequiredArgsConstructor
public class MessageListener extends ListenerAdapter {

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        // your logic
    }
}
```

## Using MongoDB

Inject `MongoTemplate` or extend `MongoRepository` — Spring Data handles the rest.

## Using Redis

Inject `RedisTemplate<String, Object>` anywhere. Keys are `String`, values are JSON-serialized via Jackson.

```java
redisTemplate.opsForValue().set("key", myObject, Duration.ofMinutes(5));
Object cached = redisTemplate.opsForValue().get("key");
```

package by.mitr.server;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

import java.time.Instant;

@Controller
@Slf4j
public class RsocketController {
    @MessageMapping("hello")
    public Mono<Void> hello(Greeting p) {
        log.info("received: {} at {}", p, Instant.now());
        return Mono.empty();
    }

    @MessageMapping("greet.{name}")
    public Mono<String> greet(@DestinationVariable String name, @Payload Greeting p) {
        log.info("received: {}, {} at {}", name, p, Instant.now());
        return Mono.just("Hello " + name + ", " + p.getMessage() + " at " + Instant.now());
    }

}

@Data
class Greeting {
    String message;
}

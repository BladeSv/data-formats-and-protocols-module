package by.mitr.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RestController()
@RequiredArgsConstructor
public class RsocketClientController {

    private final RSocketRequester requester;

    @GetMapping("hello")
    Mono<Void> hello() {
        return this.requester.route("hello").data(new Greeting("Welcome to Rsocket")).send();
    }

    @GetMapping("name/{name}")
    Mono<String> greet(@PathVariable String name) {
        return this.requester.route("greet." + name)
                .data(new Greeting("Welcome to RSocket"))
                .retrieveMono(String.class)
                .doOnNext(msg -> log.info("recevied message::" + msg));
    }

}

@Data
@AllArgsConstructor
class Greeting {

    String message;
}

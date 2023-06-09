package by.mitr.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.util.MimeTypeUtils;

@Configuration
public class RsocketConfig {

    @Bean
    public RSocketRequester rSocketRequester(RSocketRequester.Builder b) {
//        return b.dataMimeType(MimeTypeUtils.APPLICATION_JSON)
//                .connectTcp("localhost", 7000).block();
        return b.tcp("localhost", 7000);
    }
}

package by.mitr.grpcclient;



import by.mitr.grpc.PingPongServiceGrpc;
import by.mitr.grpc.PingRequest;
import by.mitr.grpc.PongResponse;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class GrpcClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(GrpcClientApplication.class, args);

        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8080)
                .usePlaintext()
                .build();

        PingPongServiceGrpc.PingPongServiceBlockingStub stub = PingPongServiceGrpc.newBlockingStub(channel);

        PongResponse response = stub.ping(PingRequest.newBuilder().setMessage("Ping").build());
        log.info("Java client revive message from server- "+response.getMessage());
        channel.shutdown();
    }
}

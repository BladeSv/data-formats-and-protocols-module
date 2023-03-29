package by.mitr.grpcserver;



import by.mitr.grpcserver.sevice.PingPongImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class GrpcServerApplication {

    public static void main(String[] args) throws IOException, InterruptedException {
        SpringApplication.run(GrpcServerApplication.class, args);

        Server server = ServerBuilder
                .forPort(8080)
                .addService(new PingPongImpl()).build();

        server.start();
        server.awaitTermination();
    }

}

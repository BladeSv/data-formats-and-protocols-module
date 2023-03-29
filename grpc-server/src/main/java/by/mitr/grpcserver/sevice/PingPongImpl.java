package by.mitr.grpcserver.sevice;

import by.mitr.grpc.PingPongServiceGrpc;
import by.mitr.grpc.PongResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PingPongImpl extends PingPongServiceGrpc.PingPongServiceImplBase {
    @Override
    public void ping(by.mitr.grpc.PingRequest request,
                      io.grpc.stub.StreamObserver<by.mitr.grpc.PongResponse> responseObserver) {
    log.info("Server get request with message- "+request.getMessage());
    PongResponse response =  PongResponse.newBuilder().setMessage("Pong").build();
    responseObserver.onNext(response);
    responseObserver.onCompleted();
}

}

import grpc

import pingpong_pb2
import pingpong_pb2_grpc

def run():
    with grpc.insecure_channel('localhost:8080') as channel:
        stub = pingpong_pb2_grpc.PingPongServiceStub(channel)

        response = stub.ping(pingpong_pb2.PingRequest(message='ping'))

    print("Python client receive response with message- "+response.message)

run()

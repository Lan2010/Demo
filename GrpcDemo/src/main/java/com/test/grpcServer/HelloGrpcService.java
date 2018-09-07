package com.test.grpcServer;

import com.test.grpc.GreeterGrpc;
import com.test.grpc.HelloReply;
import com.test.grpc.HelloRequest;

import io.grpc.stub.StreamObserver;
import net.devh.springboot.autoconfigure.grpc.server.GrpcService;

@GrpcService(GreeterGrpc.class)
public class HelloGrpcService extends GreeterGrpc.GreeterImplBase{
	@Override
	public void sayHello(HelloRequest req, StreamObserver<HelloReply> responseObserver) {
		System.out.println("server:" + req.getName());
		HelloReply reply = HelloReply.newBuilder().setMessage(("Hello: " + req.getName())).build();
		responseObserver.onNext(reply);
		responseObserver.onCompleted();
	}
}

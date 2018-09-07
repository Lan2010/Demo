package com.test.grpcClient;

import org.springframework.stereotype.Service;

import com.test.grpc.GreeterGrpc;
import com.test.grpc.HelloReply;
import com.test.grpc.HelloRequest;

import io.grpc.Channel;
import net.devh.springboot.autoconfigure.grpc.client.GrpcClient;

@Service
public class HelloGrpcClient {

	@GrpcClient("grpc-server")
	private Channel channel;
	
	public String greet(String name) {
		GreeterGrpc.GreeterBlockingStub blockingStub = GreeterGrpc.newBlockingStub(channel);
		HelloRequest request = HelloRequest.newBuilder().setName(name).build();
		HelloReply response = blockingStub.sayHello(request);
		System.out.println("----grpc - client------");
		return response.getMessage();
	}
	
	
}

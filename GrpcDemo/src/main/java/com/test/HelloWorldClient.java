package com.test;

import java.util.concurrent.TimeUnit;

import com.test.grpc.GreeterGrpc;
import com.test.grpc.HelloReply;
import com.test.grpc.HelloRequest;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

/**
 * grpc客户端 非注释
 * @author dev-lan
 * @date:   2018年10月23日 上午11:34:55
 */
public class HelloWorldClient {

	private final ManagedChannel channel;
	private final GreeterGrpc.GreeterBlockingStub blockingStub;

	
	public HelloWorldClient(String host, int port) {
		channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext(true).build();

		blockingStub = GreeterGrpc.newBlockingStub(channel);
	}

	public void shutdown() throws InterruptedException {
		channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
	}

	public void greet(String name) {
		HelloRequest request = HelloRequest.newBuilder().setName(name).build();
		HelloReply response = blockingStub.sayHello(request);
		System.out.println(response.getMessage());

	}

	public static void main(String[] args) throws InterruptedException {
		HelloWorldClient client = new HelloWorldClient("127.0.0.1", 50051);
		for (int i = 0; i < 5; i++) {
			client.greet("world:" + i);
		}
		client.shutdown();

	}
}

package com.test;

import java.io.IOException;

import com.test.grpc.GreeterGrpc;
import com.test.grpc.HelloReply;
import com.test.grpc.HelloRequest;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

/**
 * grpc服务端  非注释
 * @author dev-lan
 * @date:   2018年10月23日 上午11:35:17
 */
public class HelloWorldServer {

	private int port = 50051;
	private Server server;

	private void start() throws IOException {
		server = ServerBuilder.forPort(port).addService(new GreeterImpl()).build().start();
		System.out.println("service start...");

		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {

				System.err.println("*** shutting down gRPC server since JVM is shutting down");
				HelloWorldServer.this.stop();
				System.err.println("*** server shut down");
			}
		});
	}

	private void stop() {
		if (server != null) {
			server.shutdown();
		}
	}

	// block 一直到退出程序
	private void blockUntilShutdown() throws InterruptedException {
		if (server != null) {
			server.awaitTermination();
		}
	}

	public static void main(String[] args) throws IOException, InterruptedException {

		final HelloWorldServer server = new HelloWorldServer();
		server.start();
		server.blockUntilShutdown();
	}

	// 实现 定义一个实现服务接口的类 
	private class GreeterImpl extends GreeterGrpc.GreeterImplBase {
		public void sayHello(HelloRequest req, StreamObserver<HelloReply> responseObserver) {
			System.out.println("server:" + req.getName());
			HelloReply reply = HelloReply.newBuilder().setMessage(("Hello: " + req.getName())).build();
			responseObserver.onNext(reply);
			responseObserver.onCompleted();
		}
	}
}

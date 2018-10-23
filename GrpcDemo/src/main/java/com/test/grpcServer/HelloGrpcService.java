package com.test.grpcServer;

import com.test.grpc.GreeterGrpc;
import com.test.grpc.HelloReply;
import com.test.grpc.HelloRequest;

import io.grpc.stub.StreamObserver;
import net.devh.springboot.autoconfigure.grpc.server.GrpcService;

/**
 *    grpc服务端  实现服务接口  注释驱动
 * @author dev-lan
 * @date:   2018年10月23日 上午11:25:58
 */
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

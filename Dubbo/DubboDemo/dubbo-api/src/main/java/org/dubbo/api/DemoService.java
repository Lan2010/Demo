package org.dubbo.api;

/**
 * 定义服务接口，在服务提供方和消费方共享
 * @author Administrator
 *
 */
public interface DemoService {
	String sayHello(String name);
}

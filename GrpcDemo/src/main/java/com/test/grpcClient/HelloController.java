package com.test.grpcClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@Autowired
	private HelloGrpcClient client;
	
	@RequestMapping(value = "sayHello", method = RequestMethod.GET)
	public String hello(@RequestParam String name) {
		System.out.println("-----------hello-------");
		String message=client.greet(name);
		System.out.println("---message---:"+message);
		return name;
	}

}

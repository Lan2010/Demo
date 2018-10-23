package org.dubbo.provider;

import org.dubbo.api.DemoService;
import org.springframework.stereotype.Service;

/**
 * 服务提供方
 * @author Administrator
 *
 */
@Service("demoService")
public class DemoServiceImpl implements DemoService{

	public String sayHello(String name) {
		return name+"加点东西";
	}

}

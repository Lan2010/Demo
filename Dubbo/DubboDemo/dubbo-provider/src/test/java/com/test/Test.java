package com.test;

import java.io.IOException;

import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * 启动远程服务
 * @author Administrator
 *
 */
public class Test {

	public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:springmvc.xml");
        context.start();
        
        System.out.println("Dubbo provider start...");
        try {
            System.in.read();   // 按任意键退出
        } catch (IOException e) {
            e.printStackTrace();
        } 
	}
}

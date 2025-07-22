package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
@EntityScan("pojo")
@SpringBootApplication
@ComponentScan({"com.example.demo", "service"}) // 加上 service 包扫描
public class DemoApplication {
//启动入口
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	
	}

}

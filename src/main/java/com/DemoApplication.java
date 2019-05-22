package com;

import com.controller.chat.ChatWebSocket;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication()
@ComponentScan("com")
public class DemoApplication  {

	public static void main(String[] args) {
		SpringApplication springApplication = new SpringApplication(DemoApplication.class);
		ConfigurableApplicationContext configurableApplicationContext = springApplication.run(args);
		ChatWebSocket.setApplicationContext(configurableApplicationContext);

	}

}

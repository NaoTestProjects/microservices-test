package test.microservices.messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MessagingApplication implements CommandLineRunner{

	@Autowired
	Sender sender;

	public static void main(String[] args) {
		SpringApplication.run(MessagingApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		sender.send("Hello Messaging..!!!");
	}

}

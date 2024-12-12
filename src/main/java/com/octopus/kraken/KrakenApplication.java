package com.octopus.kraken;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.io.IOException;
import java.util.Arrays;

@SpringBootApplication(scanBasePackages = "com.octopus.kraken")
//@EnableWebMvc
public class KrakenApplication {

	public static void main(String[] args) {
		ApplicationContext atx = SpringApplication.run(KrakenApplication.class, args);
        //commandLineRunner(atx);
//        try {
//            openHomePage();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
    }
	private static void openHomePage() throws IOException {
		BrowserLauncher launcher = new BrowserLauncher();
		launcher.launchBrowser();

	}

    public static CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            System.out.println("Beans in the context:");
            Arrays.stream(ctx.getBeanDefinitionNames()).forEach(System.out::println);
        };
    }

}

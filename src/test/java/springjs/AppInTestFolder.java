package springjs;

import org.springframework.boot.builder.SpringApplicationBuilder;

public class AppInTestFolder {

	public static void main(String[] args) throws Exception {
		new SpringApplicationBuilder(App.class)
			.profiles("test")
			.application()
			.run(args);
		// org.springframework.boot.SpringApplication.run(App.class, args);
	}

}

package com.github.jonizei.clickergame;

import com.github.jonizei.clickergame.applicationuser.ApplicationUser;
import com.github.jonizei.clickergame.applicationuser.ApplicationUserRepository;
import com.github.jonizei.clickergame.applicationuser.ApplicationUserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Launcher of the application
 *
 * @author Joni Koskinen
 * @version 2020-26
 */
@Controller
@SpringBootApplication
public class ClickerGameApplication implements ErrorController {

	/**
	 * Error url
	 */
	private static final String PATH = "/error";

	/**
	 * This methods launces the application by creating new instance
	 * of ClickerGameApplication
	 *
	 * @param args Arguments from command prompt
	 */
	public static void main(String[] args) {
		SpringApplication.run(ClickerGameApplication.class, args);
	}

	/**
	 * Configures which methods, headers and origins are allowed
	 * in this application
	 *
	 * @return WebMvcConfigurer object
	 */
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedMethods("POST", "GET", "OPTIONS", "PUT", "PATCH")
						.allowedHeaders("Authorization", "Content-Type", "Content-Length", "XSRF-TOKEN", "Credentials")
						.allowedOrigins("https://localhost:3000", "http://localhost:3000", "https://my-btn-clicker-game.herokuapp.com/")
						.exposedHeaders("Authorization");
			}
		};
	}

	/**
	 * Handles all requests to "/error" url
	 * Forwards all requests to "/" url
	 *
	 * @return Url to redirect/forward
	 */
	@RequestMapping(value = PATH)
	public String error() {
		return "forward:/";
	}

	/**
	 * Returns the error url
	 *
	 * @return Error url
	 */
	@Override
	public String getErrorPath() {
		return PATH;
	}

}

package gr.filatok.library_api.api.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfiguration implements WebMvcConfigurer{
	@Override
	public void addCorsMappings(CorsRegistry reg)
	{
		reg.addMapping("/**").allowedMethods("*").allowedOrigins("*");
	}
}

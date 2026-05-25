package gr.filatok.library_api.api.configurations;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

	@Bean
	GroupedOpenApi api()
	{
		return GroupedOpenApi.builder().group("library-api").pathsToMatch("/**").build();
	}
}

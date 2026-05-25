package gr.filatok.library_api;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import gr.filatok.library_api.api.ApiExceptionHandler;
import gr.filatok.library_api.api.controllers.AuthorController;
import gr.filatok.library_api.business.dtos.AuthorDto;
import gr.filatok.library_api.business.dtos.exceptions.EntityNotFoundException;
import gr.filatok.library_api.business.services.AuthorService;

@ExtendWith(MockitoExtension.class)
public class ApiLayerTests {
	
	private MockMvc mockMvc;

	private final ObjectMapper objectMapper = new ObjectMapper();

	@Mock
	private AuthorService authorService;
	
	@BeforeEach
	void setup(){
		AuthorController controller = new AuthorController(authorService);
		mockMvc = MockMvcBuilders.standaloneSetup(controller)
				.setControllerAdvice(new ApiExceptionHandler())
				.build();
	}
	
	@Test
	void getAuthors_returnsOkWithList() throws Exception{
		AuthorDto author = new AuthorDto();
		author.setId(99);
		author.setName("Pushkin");
		when(authorService.getAuthorById(99)).thenReturn(author);
		mockMvc.perform(get("/authors/99"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.data.id").value(99))
			.andExpect(jsonPath("$.data.name").value("Pushkin"));
	}
	
	@Test
	void getAuthorById_notFound_returns404() throws Exception
	{
		when(authorService.getAuthorById(999)).thenThrow(new EntityNotFoundException("Author", 999));

		mockMvc.perform(get("/authors/999"))
				.andExpect(status().isNotFound())
				.andExpect(jsonPath("$.message").value("Author not found with id: 999"));
	}
	
	@Test
	void createAuthor_returnsCreated() throws Exception
	{
		when(authorService.createAuthor(any(AuthorDto.class))).thenReturn(1);

		AuthorDto dto = new AuthorDto();
		dto.setName("NewAuthor");

		mockMvc.perform(post("/authors")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(dto)))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.data").value(1));
	}
	
	@Test
	void createAuthor_invalidBody_returnsBadRequest() throws Exception
	{
		AuthorDto dto = new AuthorDto();
		// name is null — should fail @NotNull validation

		mockMvc.perform(post("/authors")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(dto)))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.message").isNotEmpty());
	}

	@Test
	void deleteAuthor_returnsOk() throws Exception
	{
		//doNothing().when(authorService).deleteAuthor(1);

		mockMvc.perform(delete("/authors/1"))
				.andExpect(status().isOk());

	}
}

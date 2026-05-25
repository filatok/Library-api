package gr.filatok.library_api;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LibraryApiApplicationTests {

	@Test
	void contextLoads() {
		boolean isTrue = true;

		// Ways one can test a case
		// Assertions.assertThat(OBJECT).isSOMETHING();
		Assertions.assertThat(isTrue).isTrue();
		Assertions.assertThat(isTrue).isEqualTo(true);
		Assertions.assertThat(isTrue).isNotEqualTo(false);
	}

}

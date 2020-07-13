package me.stone.aws.play.post.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.stone.aws.play.post.domain.Post;
import me.stone.aws.play.post.payload.PostReq.CreateDto;
import me.stone.aws.play.post.payload.PostReq.UpdateDto;
import me.stone.aws.play.post.repository.PostRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostControllerTest {

	@LocalServerPort
	private int port;
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Autowired
	private PostRepository postRepository;

	@Autowired
	private WebApplicationContext context;

	private MockMvc mvc;

	@BeforeEach
	void setup() {
		mvc = MockMvcBuilders
				.webAppContextSetup(context)
				.apply(springSecurity())
				.build();
	}
	
	@AfterEach
	void tearDown() throws Exception {
		postRepository.deleteAll();
	}
	
	@Test
	@WithMockUser(roles = "USER")
	@DisplayName("게시글 등록 테스트")
	void registPostTest() throws Exception {
		// given
		String title = "테스트";
		String content = "테스트 내용";
		CreateDto createDto = CreateDto.builder()
				.title(title)
				.content(content)
				.author("author")
				.build();
		
		String url ="http://localhost:" + port + "/api/v1/posts";

		//when
		mvc.perform(post(url)
				.contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(createDto)))
				.andExpect(status().isOk());

		
		// jpa
		List<Post> all = postRepository.findAll();
		assertThat(all.get(0).getTitle()).isEqualTo(title);
		assertThat(all.get(0).getContent()).isEqualTo(content);
		
	}
	
	
	@Test
	@WithMockUser(roles = "USER")
	@DisplayName("게시글 수정 테스트")
	void updatePostTest() throws Exception {
		// given
		Post createdPost = postRepository.save(Post.builder()
				.title("타이틀")
				.content("내용")
				.author("author")
				.build());

		Long savedId = createdPost.getId();
		String expectedTitle = "타이틀 수정";
		String expectedContent = "내용 수정";
		
		UpdateDto updateDto = UpdateDto.builder()
				.title(expectedTitle)
				.content(expectedContent)
				.build();
		
		String url ="http://localhost:" + port + "/api/v1/posts/" + savedId;

		// when
		mvc.perform(put(url)
				.contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(updateDto)))
				.andExpect(status().isOk());

		// then
		List<Post> all = postRepository.findAll();
		assertThat(all.get(0).getTitle()).isEqualTo(expectedTitle);
		assertThat(all.get(0).getContent()).isEqualTo(expectedContent);
		
	}
	
}

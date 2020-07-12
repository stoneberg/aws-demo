package me.stone.aws.play.post.controller;

import me.stone.aws.play.post.domain.Post;
import me.stone.aws.play.post.payload.PostReq.CreateDto;
import me.stone.aws.play.post.payload.PostReq.UpdateDto;
import me.stone.aws.play.post.repository.PostRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostControllerTest {

	@LocalServerPort
	private int port;
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Autowired
	private PostRepository postRepository;
	
	@AfterEach
	void tearDown() throws Exception {
		postRepository.deleteAll();
	}
	
	@Test
	@DisplayName("게시글 등록")
	void registPostTest() {
		// given
		String title = "title";
		String content = "content";
		CreateDto createDto = CreateDto.builder()
				.title(title)
				.content(content)
				.author("author")
				.build();
		
		String url ="http://localhost:" + port + "/api/v1/posts";
		
		// when
		ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, createDto, Long.class);
		
		// then
		
		// reponse
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(responseEntity.getBody()).isGreaterThan(0L);
		
		// jpa
		List<Post> all = postRepository.findAll();
		assertThat(all.get(0).getTitle()).isEqualTo(title);
		assertThat(all.get(0).getContent()).isEqualTo(content);
		
	}
	
	
	@Test
	@DisplayName("게시글 수정")
	void updatePostTest() {
		// given
		Post saveEntity = Post.builder()
				.title("title")
				.content("content")
				.author("author")
				.build();
		Post savedPosts = postRepository.save(saveEntity);

		Post saveEntity2 = Post.builder()
				.title("title")
				.content("content")
				.author("author")
				.build();
		Post savedPosts2 = postRepository.save(saveEntity2);
		
		Long updateId = savedPosts2.getId();
		
		String expectedTitle = "title2";
		String expectedContent = "content2";
		
		UpdateDto updateDto = UpdateDto.builder()
				.title(expectedTitle)
				.content(expectedContent)
				.build();
		
		String url ="http://localhost:" + port + "/api/v1/posts/" + updateId;
		
		HttpEntity<UpdateDto> requestEntity = new HttpEntity<>(updateDto);
		
		// when
		ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Long.class);
		
		// then
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
		System.out.println("========>"  + responseEntity.getBody());
		assertThat(responseEntity.getBody()).isGreaterThan(0L); // seq 1부터 증가하므로 저장 성공 시 0이 될수 없음
		
		List<Post> all = postRepository.findAll();
		assertThat(all.get(1).getTitle()).isEqualTo(expectedTitle);
		assertThat(all.get(1).getContent()).isEqualTo(expectedContent);
		
	}
	
}

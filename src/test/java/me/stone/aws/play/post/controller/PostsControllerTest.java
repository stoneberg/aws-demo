package me.stone.aws.play.post.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

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

import me.stone.aws.play.post.domain.Posts;
import me.stone.aws.play.post.payload.PostReq;
import me.stone.aws.play.post.payload.PostReq.SavePostDto;
import me.stone.aws.play.post.payload.PostReq.UpdatePostDto;
import me.stone.aws.play.post.repository.PostsRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostsControllerTest {

	@LocalServerPort
	private int port;
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Autowired
	private PostsRepository postsRepository;
	
	@AfterEach
	void tearDown() throws Exception {
		postsRepository.deleteAll();
	}
	
	@Test
	@DisplayName("게시글 등록")
	void registPostTest() {
		// given
		String title = "title";
		String content = "content";
		SavePostDto saveDto = SavePostDto.builder()
				.title(title)
				.content(content)
				.author("author")
				.build();
		
		String url ="http://localhost:" + port + "/api/v1/posts";
		
		// when
		ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, saveDto, Long.class);
		
		// then
		
		// reponse
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(responseEntity.getBody()).isGreaterThan(0L);
		
		// jpa
		List<Posts> all = postsRepository.findAll();
		assertThat(all.get(0).getTitle()).isEqualTo(title);
		assertThat(all.get(0).getContent()).isEqualTo(content);
		
	}
	
	
	@Test
	@DisplayName("게시글 수정")
	void updatePostTest() {
		// given
		Posts saveEntity = Posts.builder()
				.title("title")
				.content("content")
				.author("author")
				.build();
		Posts savedPosts = postsRepository.save(saveEntity);
		
		Posts saveEntity2 = Posts.builder()
				.title("title")
				.content("content")
				.author("author")
				.build();
		Posts savedPosts2 = postsRepository.save(saveEntity2);
		
		Long updateId = savedPosts2.getId();
		
		String expectedTitle = "title2";
		String expectedContent = "content2";
		
		UpdatePostDto requestDto = UpdatePostDto.builder()
				.title(expectedTitle)
				.content(expectedContent)
				.build();
		
		String url ="http://localhost:" + port + "/api/v1/posts/" + updateId;
		
		HttpEntity<UpdatePostDto> requestEntity = new HttpEntity<>(requestDto);
		
		// when
		ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Long.class);
		
		// then
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
		System.out.println("========>"  + responseEntity.getBody());
		assertThat(responseEntity.getBody()).isGreaterThan(0L); // seq 1부터 증가하므로 저장 성공 시 0이 될수 없음
		
		List<Posts> all = postsRepository.findAll();
		assertThat(all.get(1).getTitle()).isEqualTo(expectedTitle);
		assertThat(all.get(1).getContent()).isEqualTo(expectedContent);
		
	}
	
}

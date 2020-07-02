package me.stone.aws.play.post.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import me.stone.aws.play.post.domain.Posts;

@SpringBootTest
class PostRepoTest {
	
	@Autowired
	private PostsRepository postsRepository;
	
	@BeforeEach
	void init() {
		postsRepository.deleteAll();
	}
	
	@Test
	@DisplayName("게시글 저장 불러오기")
	void retrievePosts() {
		// given
		String title = "테스트 글쓰기";
		String content = "테스트본문";
		
		postsRepository.save(Posts.builder()
				.title(title)
				.content(content)
				.author("stoneberg@gmail.com")
				.build());
		
		// when
		List<Posts>	postsList = postsRepository.findAll();
		
		// then
		Posts posts = postsList.get(0);
		assertThat(posts.getTitle()).isEqualTo(title);
		assertThat(posts.getContent()).isEqualTo(content);
		
	}
	
	@Test
	@DisplayName("BaseTimeEntity 등록 확인")
	void baseTimeEntityTest() {
		// given
		LocalDateTime now = LocalDateTime.now();
		postsRepository.save(Posts.builder()
				.title("title")
				.content("content")
				.author("author")
				.build());
		// when
		List<Posts> posts = postsRepository.findAll();
		
		// then
		Posts post = posts.get(0);
		System.out.println("========> createdAt=" + post.getCreatedAt() + ", modifiedAt=" + post.getModifiedAt());
		assertThat(post.getCreatedAt()).isAfter(now);
		assertThat(post.getModifiedAt()).isAfter(now);
	}

}

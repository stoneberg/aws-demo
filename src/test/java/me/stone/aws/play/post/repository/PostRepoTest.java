package me.stone.aws.play.post.repository;

import me.stone.aws.play.post.domain.Post;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class PostRepoTest {
	
	@Autowired
	private PostRepository postRepository;
	
	@BeforeEach
	void init() {
		postRepository.deleteAll();
	}
	
	@Test
	@DisplayName("게시글 저장 불러오기")
	void retrievePosts() {
		// given
		String title = "테스트 글쓰기";
		String content = "테스트본문";
		
		postRepository.save(Post.builder()
				.title(title)
				.content(content)
				.author("stoneberg@gmail.com")
				.build());
		
		// when
		List<Post>	postsList = postRepository.findAll();
		
		// then
		Post post = postsList.get(0);
		assertThat(post.getTitle()).isEqualTo(title);
		assertThat(post.getContent()).isEqualTo(content);
		
	}
	
	@Test
	@DisplayName("BaseTimeEntity 등록 확인")
	void baseTimeEntityTest() {
		// given
		LocalDateTime now = LocalDateTime.now();
		postRepository.save(Post.builder()
				.title("title")
				.content("content")
				.author("author")
				.build());
		// when
		List<Post> posts = postRepository.findAll();
		
		// then
		Post post = posts.get(0);
		System.out.println("========> createdAt=" + post.getCreatedAt() + ", modifiedAt=" + post.getModifiedAt());
		assertThat(post.getCreatedAt()).isAfter(now);
		assertThat(post.getModifiedAt()).isAfter(now);
	}

}

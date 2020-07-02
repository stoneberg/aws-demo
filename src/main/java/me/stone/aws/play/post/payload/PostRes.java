package me.stone.aws.play.post.payload;

import lombok.Getter;
import me.stone.aws.play.post.domain.Posts;

public class PostRes {

	@Getter
	public static class FindPostDto {

		private Long id;
		private String title;
		private String content;
		private String author;

		public FindPostDto(Posts posts) {
			this.id = posts.getId();
			this.title = posts.getTitle();
			this.content = posts.getContent();
			this.author = posts.getAuthor();
		}

	}
}

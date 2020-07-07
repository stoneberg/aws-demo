package me.stone.aws.play.post.payload;

import lombok.Getter;
import me.stone.aws.play.post.domain.Posts;

import java.time.LocalDateTime;

public class PostRes {

	@Getter
	public static class FindPostDto {

		private Long id;
		private String title;
		private String content;
		private String author;
		private LocalDateTime modifiedAt;

		public FindPostDto(Posts entity) {
			this.id = entity.getId();
			this.title = entity.getTitle();
			this.content = entity.getContent();
			this.author = entity.getAuthor();
			this.modifiedAt = entity.getModifiedAt();
		}

	}
}

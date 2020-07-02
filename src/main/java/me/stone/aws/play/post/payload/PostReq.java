package me.stone.aws.play.post.payload;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.stone.aws.play.post.domain.Posts;

public class PostReq {

	@Getter
	@NoArgsConstructor
	public static class SavePostDto {

		private String title;
		private String content;
		private String author;

		@Builder
		public SavePostDto(String title, String content, String author) {
			super();
			this.title = title;
			this.content = content;
			this.author = author;
		}

		public Posts toEntity() {
			return Posts.builder()
					.title(title)
					.content(content)
					.author(author)
					.build();
		}

	}
	
	@Getter
	@NoArgsConstructor
	public static class UpdatePostDto {

		private String title;
		private String content;

		@Builder
		public UpdatePostDto(String title, String content, String author) {
			super();
			this.title = title;
			this.content = content;
		}

		public Posts toEntity() {
			return Posts.builder()
					.title(title)
					.content(content)
					.build();
		}

	}

}

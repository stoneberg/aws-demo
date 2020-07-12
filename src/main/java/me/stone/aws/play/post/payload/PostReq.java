package me.stone.aws.play.post.payload;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.stone.aws.play.post.domain.Post;

public class PostReq {

	@Getter
	@NoArgsConstructor
	public static class CreateDto {

		private String title;
		private String content;
		private String author;

		@Builder
		public CreateDto(String title, String content, String author) {
			super();
			this.title = title;
			this.content = content;
			this.author = author;
		}

		public Post toEntity() {
			return Post.builder()
					.title(this.title)
					.content(this.content)
					.author(this.author)
					.build();
		}

	}
	
	@Getter
	@NoArgsConstructor
	public static class UpdateDto {

		private String title;
		private String content;

		@Builder
		public UpdateDto(String title, String content, String author) {
			super();
			this.title = title;
			this.content = content;
		}

		public Post toEntity() {
			return Post.builder()
					.title(this.title)
					.content(this.content)
					.build();
		}

	}

}

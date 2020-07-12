package me.stone.aws.play.post.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.stone.aws.play.common.entity.BaseTimeEntity;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED )
@Entity
public class Post extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 500, nullable = false)
	private String title;

	@Column(columnDefinition = "TEXT", nullable = false)
	private String content;

	private String author;

	@Builder
	public Post(String title, String content, String author) {
		super();
		this.title = title;
		this.content = content;
		this.author = author;
	}
	
	// biz logic (domain driven model)
	public void update(String title, String content) {
		this.title = title;
		this.content = content;
	}

}

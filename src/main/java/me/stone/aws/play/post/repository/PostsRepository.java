package me.stone.aws.play.post.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import me.stone.aws.play.post.domain.Posts;

public interface PostsRepository extends JpaRepository<Posts, Long>{

}

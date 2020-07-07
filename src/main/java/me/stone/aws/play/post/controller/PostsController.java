package me.stone.aws.play.post.controller;

import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import me.stone.aws.play.post.payload.PostReq.SavePostDto;
import me.stone.aws.play.post.payload.PostReq.UpdatePostDto;
import me.stone.aws.play.post.payload.PostRes.FindPostDto;
import me.stone.aws.play.post.service.PostsService;

@RequiredArgsConstructor
@RestController
public class PostsController {
	
	private final PostsService postService;
	
	@PostMapping("/api/v1/posts")
	public Long save(@RequestBody SavePostDto requestDto) {
		return postService.save(requestDto);
	}
	
	@PutMapping("/api/v1/posts/{id}")
	public Long update(@PathVariable Long id, @RequestBody UpdatePostDto requestDto) {
		return postService.update(id, requestDto);
	}
	
	@GetMapping("/api/v1/posts/{id}")
	public FindPostDto findById(@PathVariable Long id) {
		return postService.findById(id);
	}

	@DeleteMapping("/api/v1/posts/{id}")
	public Long delete(@PathVariable Long id) {
		postService.delete(id);
		return id;
	}

}

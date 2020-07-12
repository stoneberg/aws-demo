package me.stone.aws.play.post.controller;

import lombok.RequiredArgsConstructor;
import me.stone.aws.play.post.payload.PostReq;
import me.stone.aws.play.post.payload.PostReq.UpdateDto;
import me.stone.aws.play.post.service.PostService;
import org.springframework.web.bind.annotation.*;

import static me.stone.aws.play.post.payload.PostRes.FindDto;

@RequiredArgsConstructor
@RestController
public class PostController {
	
	private final PostService postService;
	
	@PostMapping("/api/v1/posts")
	public Long save(@RequestBody PostReq.CreateDto createDto) {
		return postService.save(createDto);
	}
	
	@PutMapping("/api/v1/posts/{id}")
	public Long update(@PathVariable Long id, @RequestBody UpdateDto updateDto) {
		return postService.update(id, updateDto);
	}
	
	@GetMapping("/api/v1/posts/{id}")
	public FindDto findById(@PathVariable Long id) {
		return postService.findById(id);
	}

	@DeleteMapping("/api/v1/posts/{id}")
	public Long delete(@PathVariable Long id) {
		postService.delete(id);
		return id;
	}

}

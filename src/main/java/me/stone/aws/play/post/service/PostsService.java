package me.stone.aws.play.post.service;


import me.stone.aws.play.post.payload.PostRes;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.stone.aws.play.post.domain.Posts;
import me.stone.aws.play.post.payload.PostReq.SavePostDto;
import me.stone.aws.play.post.payload.PostReq.UpdatePostDto;
import me.stone.aws.play.post.payload.PostRes.FindPostDto;
import me.stone.aws.play.post.repository.PostsRepository;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class PostsService {
	
	private final PostsRepository postsRepository;

	@Transactional
	public Long save(SavePostDto requestDto) {
		return postsRepository.save(requestDto.toEntity()).getId();
	}

	@Transactional
	public Long update(Long id, UpdatePostDto requestDto) {
		Posts posts = postsRepository.findById(id).orElseThrow(()-> new IllegalArgumentException(String.format("해당 게시글이 없습니다. id=%s", id)));
		log.info("============================================={}", requestDto);
		posts.update(requestDto.getTitle(), requestDto.getContent());
		log.info("============================================={}", requestDto);
		return id;
	}

	public FindPostDto findById(Long id) {
		Posts posts = postsRepository.findById(id).orElseThrow(()-> new IllegalArgumentException(String.format("해당 게시글이 없습니다. id=%s", id)));
		return new FindPostDto(posts);
	}

	public List<FindPostDto> findAllDesc() {
		return postsRepository.findAllDesc().stream()
				.map(FindPostDto::new)
				.collect(Collectors.toList());
	}

}

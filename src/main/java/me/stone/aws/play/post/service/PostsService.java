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
@Transactional(readOnly = true)
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    /**
     * 글 등록
     *
     * @param requestDto
     * @return
     */
    @Transactional
    public Long save(SavePostDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    /**
     * 글 수정
     *
     * @param id
     * @param requestDto
     * @return
     */
    @Transactional
    public Long update(Long id, UpdatePostDto requestDto) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(String.format("해당 게시글이 없습니다. id=%s", id)));
        posts.update(requestDto.getTitle(), requestDto.getContent());
        return id;
    }

    /**
     * 글 조회
     *
     * @param id
     * @return
     */
    public FindPostDto findById(Long id) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(String.format("해당 게시글이 없습니다. id=%s", id)));
        return new FindPostDto(posts);
    }

    /**
     * 글 리스트 조회
     *
     * @return
     */
    public List<FindPostDto> findAllDesc() {
        return postsRepository.findAllDesc().stream()
                .map(FindPostDto::new)
                .collect(Collectors.toList());
    }

    /**
     * 글 삭제
     */
    @Transactional
    public void delete(Long id) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(String.format("해당 게시글이 없습니다.id=%s", id)));
        postsRepository.delete(posts);
    }

}

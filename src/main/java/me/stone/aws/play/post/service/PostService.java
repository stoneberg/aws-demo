package me.stone.aws.play.post.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.stone.aws.play.post.domain.Post;
import me.stone.aws.play.post.payload.PostReq.CreateDto;
import me.stone.aws.play.post.payload.PostReq.UpdateDto;
import me.stone.aws.play.post.payload.PostRes.FindDto;
import me.stone.aws.play.post.repository.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class PostService {

    private final PostRepository postRepository;

    /**
     * 글 등록
     *
     * @param createDto
     * @return
     */
    @Transactional
    public Long save(CreateDto createDto) {
        return postRepository.save(createDto.toEntity()).getId();
    }

    /**
     * 글 수정
     *
     * @param id
     * @param updateDto
     * @return
     */
    @Transactional
    public Long update(Long id, UpdateDto updateDto) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(String.format("해당 게시글이 없습니다. id=%s", id)));
        post.update(updateDto.getTitle(), updateDto.getContent());
        return id;
    }

    /**
     * 글 조회
     *
     * @param id
     * @return
     */
    public FindDto findById(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(String.format("해당 게시글이 없습니다. id=%s", id)));
        return new FindDto(post);
    }

    /**
     * 글 리스트 조회
     *
     * @return
     */
    public List<FindDto> findAllDesc() {
        return postRepository.findAllDesc().stream()
                .map(FindDto::new)
                .collect(Collectors.toList());
    }

    /**
     * 글 삭제
     */
    @Transactional
    public void delete(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(String.format("해당 게시글이 없습니다.id=%s", id)));
        postRepository.delete(post);
    }

}

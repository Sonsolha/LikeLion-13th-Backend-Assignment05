package com.likelion.likelioncrud.tag.application;

import com.likelion.likelioncrud.tag.api.dto.request.TagSaveRequestDto;
import com.likelion.likelioncrud.tag.api.dto.request.TagUpdateRequestDto;
import com.likelion.likelioncrud.tag.api.dto.response.TagInfoResponseDto;
import com.likelion.likelioncrud.tag.api.dto.response.TagListResponseDto;
import com.likelion.likelioncrud.tag.domain.Tag;
import com.likelion.likelioncrud.tag.domain.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TagService {

    private final TagRepository tagRepository;

    // 1. 태그 저장
    @Transactional
    public void save(TagSaveRequestDto requestDto) {
        Tag tag = Tag.builder()
                .name(requestDto.name())
                .build();
        tagRepository.save(tag);
    }

    // 2. 태그 전체 조회
    public TagListResponseDto findAll() {
        List<Tag> tags = tagRepository.findAll();
        List<TagInfoResponseDto> responseList = tags.stream()
                .map(TagInfoResponseDto::from)
                .toList();
        return TagListResponseDto.from(responseList);
    }

    // 3. 태그 단건 조회
    public TagInfoResponseDto findOne(Long id) {
        Tag tag = tagRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 태그가 없습니다."));
        return TagInfoResponseDto.from(tag);
    }

    // 4. 태그 수정
    @Transactional
    public void update(Long id, TagUpdateRequestDto requestDto) {
        Tag tag = tagRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 태그가 없습니다."));
        tag.update(requestDto.name());
    }

    // 5. 태그 삭제
    @Transactional
    public void delete(Long id) {
        Tag tag = tagRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 태그가 없습니다."));
        tagRepository.delete(tag);
    }
}

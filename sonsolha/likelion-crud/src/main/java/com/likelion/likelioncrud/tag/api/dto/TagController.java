package com.likelion.likelioncrud.tag.api;

import com.likelion.likelioncrud.tag.api.dto.request.TagSaveRequestDto;
import com.likelion.likelioncrud.tag.api.dto.request.TagUpdateRequestDto;
import com.likelion.likelioncrud.tag.api.dto.response.TagInfoResponseDto;
import com.likelion.likelioncrud.tag.api.dto.response.TagListResponseDto;
import com.likelion.likelioncrud.tag.application.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tag")
@RequiredArgsConstructor
public class TagController {

    private final TagService tagService;

    // 태그 저장
    @PostMapping
    public ResponseEntity<String> save(@RequestBody TagSaveRequestDto requestDto) {
        tagService.save(requestDto);
        return new ResponseEntity<>("태그 저장 성공!", HttpStatus.CREATED);
    }

    // 전체 태그 목록 조회
    @GetMapping
    public ResponseEntity<TagListResponseDto> findAll() {
        return new ResponseEntity<>(tagService.findAll(), HttpStatus.OK);
    }

    // 단건 태그 조회
    @GetMapping("/{id}")
    public ResponseEntity<TagInfoResponseDto> findOne(@PathVariable Long id) {
        return new ResponseEntity<>(tagService.findOne(id), HttpStatus.OK);
    }

    // 태그 수정
    @PatchMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody TagUpdateRequestDto requestDto) {
        tagService.update(id, requestDto);
        return new ResponseEntity<>("태그 수정 성공!", HttpStatus.OK);
    }

    // 태그 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        tagService.delete(id);
        return new ResponseEntity<>("태그 삭제 성공!", HttpStatus.OK);
    }
}

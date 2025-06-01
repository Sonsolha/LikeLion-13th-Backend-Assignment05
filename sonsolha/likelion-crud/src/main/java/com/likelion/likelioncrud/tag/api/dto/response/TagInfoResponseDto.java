package com.likelion.likelioncrud.tag.api.dto.response;

import com.likelion.likelioncrud.tag.domain.Tag;

public record TagInfoResponseDto(
        Long id,
        String name
) {
    public static TagInfoResponseDto from(Tag tag) {
        return new TagInfoResponseDto(tag.getTagId(), tag.getName());
    }
}

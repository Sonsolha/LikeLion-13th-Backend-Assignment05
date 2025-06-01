package com.likelion.likelioncrud.tag.api.dto.response;

import java.util.List;

public record TagListResponseDto(
        List<TagInfoResponseDto> tags
) {
    public static TagListResponseDto from(List<TagInfoResponseDto> tags) {
        return new TagListResponseDto(tags);
    }
}

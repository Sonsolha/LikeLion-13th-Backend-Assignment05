package com.likelion.likelioncrud.posttag.api.dto.response;

import com.likelion.likelioncrud.posttag.domain.PostTag;

public record PostTagResponseDto(
        String tagName
) {
    public static PostTagResponseDto from(PostTag postTag) {
        return new PostTagResponseDto(postTag.getTag().getName());
    }
}

package com.likelion.likelioncrud.post.api.dto.response;

import com.likelion.likelioncrud.post.domain.Post;
import lombok.Builder;

import java.util.List;

@Builder
public record PostInfoResponseDto(
        String title,
        String contents,
        String writer,
        List<String> tags
) {
    public static PostInfoResponseDto from(Post post) {
        List<String> tags = post.getPostTags().stream()
                .map(postTag -> postTag.getTag().getName())
                .toList();

        return new PostInfoResponseDto(
                post.getTitle(),
                post.getContents(),
                post.getMember().getName(),
                tags
        );
    }
}

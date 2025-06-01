package com.likelion.likelioncrud.post.application;

import com.likelion.likelioncrud.member.domain.Member;
import com.likelion.likelioncrud.member.domain.repository.MemberRepository;
import com.likelion.likelioncrud.post.api.dto.request.PostSaveRequestDto;
import com.likelion.likelioncrud.post.api.dto.request.PostUpdateRequestDto;
import com.likelion.likelioncrud.post.api.dto.response.PostInfoResponseDto;
import com.likelion.likelioncrud.post.api.dto.response.PostListResponseDto;
import com.likelion.likelioncrud.post.domain.Post;
import com.likelion.likelioncrud.post.domain.repository.PostRepository;
import com.likelion.likelioncrud.posttag.domain.PostTag;
import com.likelion.likelioncrud.posttag.domain.repository.PostTagRepository;
import com.likelion.likelioncrud.tag.domain.Tag;
import com.likelion.likelioncrud.tag.domain.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

    private final MemberRepository memberRepository;
    private final PostRepository postRepository;
    private final TagRepository tagRepository;
    private final PostTagRepository postTagRepository;

    @Transactional
    public void postSave(PostSaveRequestDto postSaveRequestDto) {
        Member member = memberRepository.findById(postSaveRequestDto.memberId())
                .orElseThrow(() -> new IllegalArgumentException("해당 멤버가 없습니다"));

        Post post = Post.builder()
                .title(postSaveRequestDto.title())
                .contents(postSaveRequestDto.contents())
                .member(member)
                .build();

        postRepository.save(post);

        for (String tagName : postSaveRequestDto.tags()) {
            Tag tag = tagRepository.findByName(tagName)
                    .orElseGet(() -> tagRepository.save(new Tag(tagName)));

            PostTag postTag = new PostTag(post, tag);
            postTagRepository.save(postTag);
        }
    }

    public PostListResponseDto postFindMember(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(IllegalArgumentException::new);

        List<Post> posts = postRepository.findByMember(member);
        List<PostInfoResponseDto> responseDtos = posts.stream()
                .map(PostInfoResponseDto::from)
                .toList();

        return PostListResponseDto.from(responseDtos);
    }

    @Transactional
    public void postUpdate(Long postId, PostUpdateRequestDto postUpdateRequestDto) {
        Post post = postRepository.findById(postId)
                .orElseThrow(IllegalArgumentException::new);
        post.update(postUpdateRequestDto);
    }

    @Transactional
    public void postDelete(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(IllegalArgumentException::new);
        postRepository.delete(post);
    }
}

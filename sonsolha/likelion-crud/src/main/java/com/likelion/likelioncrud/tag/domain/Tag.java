package com.likelion.likelioncrud.tag.domain;

import com.likelion.likelioncrud.post.api.dto.request.PostUpdateRequestDto;
import com.likelion.likelioncrud.posttag.domain.PostTag;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id")
    public Long tagId;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "tag", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PostTag> postTags = new ArrayList<>();

    public Tag(String name) {
        this.name = name;
    }

    @Builder
    public static Tag create(String name) {
        return new Tag(name);
    }

    public void update(PostUpdateRequestDto postUpdateRequestDto) {
        this.name = name;
    }

    public void update(String name) {
        this.name = name;
    }
}

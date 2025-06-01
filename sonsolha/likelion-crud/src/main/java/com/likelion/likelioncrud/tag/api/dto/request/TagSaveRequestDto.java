package com.likelion.likelioncrud.tag.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record TagSaveRequestDto(

        @NotBlank(message = "태그 이름은 필수로 입력해야 합니다.")
        @Size(min = 1, max = 20, message = "태그 이름은 1~20자 사이여야 합니다.")
        String name

) {}

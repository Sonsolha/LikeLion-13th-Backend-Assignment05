package com.likelion.likelioncrud.common.error;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ErrorCode {

    //404
    MEMBER_NOT_FOUND_EXCEPTION(HttpStatus.NOT_FOUND, "해당 사용자가 없습니다. memberId = ", "NOT_FOUND_404"),
    POST_NOT_FOUND_EXCEPTION(HttpStatus.NOT_FOUND, "해당 게시글이 없습니다. postId = ", "NOT_FOUND_404"),

    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "내부 서버 오류가 발생했습니다.", "INTERNAL_SERVER_ERROR_500"),

    //400
    VALIDATION_EXCEPTION(HttpStatus.BAD_REQUEST, "유효성 검사에 실패하였습니다", "BAD_REQUEST_400");

    // 500

    private final HttpStatus httpStatus;
    private final String message;
    private final String code;

    public int getHttpStatusCode() {
        return httpStatus.value();
    }

}

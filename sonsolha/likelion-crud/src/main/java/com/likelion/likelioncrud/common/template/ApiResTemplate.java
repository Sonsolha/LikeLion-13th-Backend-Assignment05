package com.likelion.likelioncrud.common.template;

import com.likelion.likelioncrud.common.error.ErrorCode;
import com.likelion.likelioncrud.common.error.SuccessCode;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor(access =  AccessLevel.PRIVATE)
@RequiredArgsConstructor(access =  AccessLevel.PRIVATE)
public class ApiResTemplate<T> {
    private final int code; //응답 코드
    private final String message; // 응답 메시지
    private T data; //응답 데이터

    //데이터가 없는 성공 응답
    public static ApiResTemplate successWithNoContent(SuccessCode successCode) {
        return new ApiResTemplate<>(successCode.getHttpStatusCode(), successCode.getMessage());
    }

    // 데이터가 있는 성공읍답
    public static <T> ApiResTemplate<T> successRespons(SuccessCode successCode, T data) {
        return new ApiResTemplate<>(successCode.getHttpStatusCode(), successCode.getMessage(), data);
    }

    // 에러응답(메세지 포함)

    public static ApiResTemplate errorResponse(ErrorCode errorCode, String customMessage) {
        return new ApiResTemplate<>(errorCode.getHttpStatusCode(),customMessage);
    }
}

package com.hodolog.api.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import jakarta.validation.constraints.NotBlank;

@Setter
@Getter
@ToString
public class PostCreate {
    @NotBlank(message = "타이틀을 입력해주세요.")
    private String title;

    @NotBlank(message = "콘텐츠를 입력해주세요.")
    private String content;

    //순서로 인한 휴먼 에러 가능 builder 사용
    //클래스에 달면 NoArgsConstructor와 충돌 가능성 있음
    @Builder
    public PostCreate(String title, String content) {
        this.title = title;
        this.content = content;
    }
    // 빌더의 장점
    // 가독성이 좋다
    // 값 생성에 대힌 유연함
    //필요한 값만 받을 수 있다 - 오버로딩 가능한 조건?
    // 객체의 불변성

}

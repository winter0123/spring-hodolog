package com.hodolog.api.response;

import lombok.Builder;
import lombok.Getter;
/*
* 서비스 정책에 맞는
* */
@Getter
public class PostResponse {
    private Long id;
    private String title;
    private String content;

    @Builder
    public PostResponse(Long id, String title, String content) {
        this.id = id;
        this.title = title.substring(0,Math.min(title.length(),10));
        this.content = content;
    }
}

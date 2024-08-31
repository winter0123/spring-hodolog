package com.hodolog.api.service;

import com.hodolog.api.domain.Post;
import com.hodolog.api.repository.PostRepository;
import com.hodolog.api.request.PostCreate;
import com.hodolog.api.response.PostResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public void write(PostCreate postCreate){
        //Post post = new Post(postCreate.getTitle(),postCreate.getContent());
        Post post = Post.builder()
                .title(postCreate.getTitle())
                .content(postCreate.getContent())
                .build();
        //return postRepository.save(post);
        //return postRepository.save(post).getId();
        postRepository.save(post);
    }

    public PostResponse get(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 글입니다"));
        return PostResponse.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .build();
    }

//    public List<PostResponse> getList() {
//
////        Post p = Post.builder()
////                .title("title")
////                .content("content")
////                .build();
////        PostResponse pr = new PostResponse(p.getId(), p.getTitle(), p.getContent());
//
//        return postRepository.findAll().stream()
//                .map(post -> PostResponse.builder()
//                        .id(post.getId())
//                        .title(post.getTitle())
//                        .content(post.getContent())
//                        .build())
//                .collect(Collectors.toList());
//    }

    public List<PostResponse> getList() {
        return postRepository.findAll().stream()
                //.map(post -> new PostResponse(post)) //생성자 오버로딩
                .map(PostResponse::new) //생성자 오버로딩을 java8 메소드 레퍼런스 형태로 변경(위,아래 같은 코드)
                .collect(Collectors.toList());
    }
}

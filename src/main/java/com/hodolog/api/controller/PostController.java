package com.hodolog.api.controller;

import com.hodolog.api.request.PostCreate;
import com.hodolog.api.response.PostResponse;
import com.hodolog.api.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/posts")
    public void post(@RequestBody @Valid PostCreate request) {
        postService.write(request);
    }

    @GetMapping("/posts/{postId}")
    public PostResponse get(@PathVariable(name= "postId") Long postId){
        return postService.get(postId);
    }

    @GetMapping("/posts")
    public List<PostResponse> getList() {
        return postService.getList();
    }


//    @PostMapping("/posts")
//    public String post(@RequestParam String title, @RequestParam String content){
//        log.info("title={},content={}",title,content);
//        return "Hello World";
//    }

//    @PostMapping("/posts")
//    public String post(@RequestParam Map<String,String> params){
//        log.info("params={}",params);
//        String title = params.get("title");
//        return "Hello World";
//    }

    //데이터를 검증하는 이유
    //1. client 개발자가 깜빡할수 있다. 실수로 값을 안보낼 수 있다.
    //2. client bug로 값이 누락될 수 있다.
    //3. 외부에 나쁜 사람이 값을 임의로 조작해서 보낼 수 있다.
    //4. db에 값을 저장할 때 의도치 않은 오류가 발생할 수 있다.
    //5. 서버 개발자의 편안함을 위해서

//    @PostMapping("/posts")
//    public Map<String,String> post(@RequestBody @Valid PostCreate params, BindingResult result) throws Exception {
//        log.info("params={}",params.toString());
//        if(result.hasErrors()) {
//            List<FieldError> fieldErrors = result.getFieldErrors();
//            FieldError firstFieldError = fieldErrors.get(0);
//            String fieldName = firstFieldError.getField(); //title
//            String errorMessage = firstFieldError.getDefaultMessage(); //..에러메시지
//
//            Map<String,String> error = new HashMap<>();
//            error.put(fieldName, errorMessage);
//            return error;
//        }
//
//        return Map.of();
//    }

    //Case1. 저장한 데이터 Entity -> response로 응답하기
    //Case2. 저장한 데이터의 primary_id -> response로 응답하기
    //      Client에서는 수신한 id를 글 조회 API를 통해서 데이터를 수신받음 수정 삭제를 위해
    //Case3. 응답필요 없음 -> 클라이언트에서 모든 POST(글) 데이터 context를 잘 관리함
    //      서버에서 차라리 유연하게 대응하는 게 좋습니다.
    //      한 번에 일괄적으로 잘 처리되는 케이스가 없습니다. -> 잘관리하는 형태가 중요합니다.
    //Long postId = postService.write(request);
    //return Map.of("postId",postId);

}

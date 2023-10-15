package com.sikimara.products.Reviews;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products/posts")
@AllArgsConstructor
public class PostController {
    PostRepository postRepository;
    @PostMapping("/create")
    public Posts createPost(@RequestBody Posts post){
        return postRepository.save(post);
    }
    @GetMapping("/all")
    public Iterable<Posts> getAllPosts(){
        return postRepository.findAll();
    }
}

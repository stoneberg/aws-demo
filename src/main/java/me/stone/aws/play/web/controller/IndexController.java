package me.stone.aws.play.web.controller;

import lombok.RequiredArgsConstructor;
import me.stone.aws.play.post.payload.PostRes;
import me.stone.aws.play.post.service.PostsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("posts", postsService.findAllDesc());
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostRes.FindPostDto dto = postsService.findById(id);
        model.addAttribute("post", dto);
        return "posts-update";
    }

}

package me.stone.aws.play.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.stone.aws.play.common.annotations.LoginUser;
import me.stone.aws.play.config.auth.dto.SessionUser;
import me.stone.aws.play.post.payload.PostRes.FindDto;
import me.stone.aws.play.post.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@Slf4j
@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostService postService;
    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {
        model.addAttribute("posts", postService.findAllDesc());
        if (user != null) {
            model.addAttribute("user_name", user.getName());
        }
        return "index";
    }

    @GetMapping("/post/create")
    public String postSave() {
        return "post-create";
    }

    // just test
    @GetMapping("/post/detail/{id}")
    public String postDetail(@PathVariable Long id, Model model) {
        FindDto findDto = postService.findById(id);
        model.addAttribute("post", findDto);
        return "post-update";
    }

}

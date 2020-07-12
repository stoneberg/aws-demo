package me.stone.aws.play.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.stone.aws.play.config.auth.dto.SessionUser;
import me.stone.aws.play.post.payload.PostRes;
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
    public String index(Model model) {
        log.info("@index========================>");
        model.addAttribute("posts", postService.findAllDesc());
        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        log.info("@index.user========================>{}", user);
        if(user != null){
            model.addAttribute("username", user.getName());
        }
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

    // just test
    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        FindDto findDto = postService.findById(id);
        model.addAttribute("post", findDto);
        return "posts-update";
    }

}

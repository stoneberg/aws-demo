package me.stone.aws.play.web.controller;

import me.stone.aws.play.web.payload.HelloResDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/hello/dto")
    public HelloResDTO helloDto(@RequestParam("name") String name, @RequestParam("amount") int amount) {
        return new HelloResDTO(name, amount);
    }
}

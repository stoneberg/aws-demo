package me.stone.aws.play.web.payload;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class HelloResDTO {
    private final String name;
    private final int amount;
}

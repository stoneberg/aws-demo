package me.stone.aws.play.web.payload;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HelloResDtoTest {

    @Test
    public void rombokTest() {
        // given
        String name = "stoneberg";
        int amount = 10000;

        // when
        HelloResDTO dto = new HelloResDTO(name, amount);

        // then
        assertThat(dto.getName()).isEqualTo(name);
        assertThat(dto.getAmount()).isEqualTo(amount);
    }

}

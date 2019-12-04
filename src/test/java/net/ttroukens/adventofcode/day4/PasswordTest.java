package net.ttroukens.adventofcode.day4;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PasswordTest {

    @Test
    public void test() {
        assertThat(Password.isFittingPassword("111111")).isTrue();
        assertThat(Password.isFittingPassword("223450")).isFalse();
        assertThat(Password.isFittingPassword("123789")).isFalse();
    }
}

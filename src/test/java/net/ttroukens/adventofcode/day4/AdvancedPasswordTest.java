package net.ttroukens.adventofcode.day4;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AdvancedPasswordTest {

    @Test
    public void test() {
        assertThat(AdvancedPassword.isAdvancedFittingPassword("112233")).isTrue();
        assertThat(AdvancedPassword.isAdvancedFittingPassword("123444")).isFalse();
        assertThat(AdvancedPassword.isAdvancedFittingPassword("111122")).isTrue();
    }
}

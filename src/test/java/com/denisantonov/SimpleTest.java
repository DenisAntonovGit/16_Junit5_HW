package com.denisantonov;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SimpleTest {

    @Disabled
    @DisplayName("Этот тест проверяет кое-что")
    @Test
    void firstTest() {
        assertTrue(3 > 2);

    }
    @DisplayName("Еще одна проверка кое-чего")
    @Test
    void secondTest() {
        assertTrue(3 > 1);

    }
}

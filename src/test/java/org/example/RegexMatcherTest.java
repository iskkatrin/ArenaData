package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RegexMatcherTest {

    @Test
    public void testMatches_validRegex_validText() {
        String regex = "\\d+";  // регулярное выражение для чисел
        String text = "123";

        // Проверяем, что метод возвращает true для правильного совпадения
        assertTrue(RegexMatcher.matches(regex, text));
    }

    @Test
    public void testMatches_invalidRegex_validText() {
        String regex = "\\d+";  // регулярное выражение для чисел
        String text = "abc";

        // Проверяем, что метод возвращает false для несовпадения
        assertFalse(RegexMatcher.matches(regex, text));
    }

    @Test
    public void testMatches_nullRegex() {
        // Проверка, что метод выбрасывает исключение при null значении regex
        assertThrows(InvalidRegexArgumentException.class, () -> {
            RegexMatcher.matches(null, "text");
        });
    }

    @Test
    public void testMatches_nullText() {
        // Проверка, что метод выбрасывает исключение при null значении text
        assertThrows(InvalidRegexArgumentException.class, () -> {
            RegexMatcher.matches("\\d+", null);
        });
    }

    @Test
    public void testMatches_cacheEfficiency() {
        String regex = "\\d+";  // регулярное выражение для чисел
        String text = "123";

        // Проверка, что кэширование работает (тот же результат на втором вызове)
        RegexMatcher.matches(regex, text);
        assertTrue(RegexMatcher.matches(regex, text));
    }
}

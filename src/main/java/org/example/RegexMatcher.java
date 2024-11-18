package org.example;

import java.util.logging.Logger;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class RegexMatcher {
    /**
     * Класс RegexMatcher предоставляет метод для проверки соответствия строки регулярному выражению.
     *
     * Проблемы в исходном коде:
     *    - Метод Pattern.compile(regex) может выбрасывать исключение PatternSyntaxException, если переданное регулярное выражение имеет синтаксическую ошибку.
     * 1. **Отсутствие обработки исключений для некорректного выражения:** Это исключение не обрабатывается, что может привести к сбою.
     *    - Я добавила обработку исключений.
     *
     * 2. **Отсутствие проверки на null для параметров:**
     *    - Если переданы null значения для параметров regex или text, метод вызовет NullPointerException.
     *    - Я добавила проверку на null и выброс исключения InvalidRegexArgumentException с объяснением ошибки.
     *
     * 3. **Использование обертки Boolean вместо примитивного типа boolean:**
     *    - В исходном коде использована обертка Boolean для хранения результата метода matches(), который уже возвращает примитивный тип boolean.
     *    - Я заменила объект Boolean на примитивный тип boolean для повышения производительности.
     *
     * 4. **Отсутствие кэширования регулярных выражений:**
     *    - Каждый раз при вызове метода Pattern.compile(regex) происходит компиляция регулярного выражения, что приводит к излишним затратам времени на компиляцию.
     *    - Я добавила кэширование скомпилированных регулярных выражений в ConcurrentHashMap для повышения производительности.
     *
     * 5. **Отсутствие логирования ошибок:**
     *    - При возникновении исключений, например, при синтаксической ошибке в регулярном выражении, ошибка не логируется, что затрудняет диагностику.
     *    - Я добавила логирование с помощью Logger для отслеживания проблем и упрощения отладки.
     *
     * Решения:
     * - Обработка исключений для неправильного синтаксиса регулярного выражения.
     * - Проверка параметров на null с выбросом исключения InvalidRegexArgumentException.
     * - Использование примитивного типа boolean вместо Boolean.
     * - Кэширование скомпилированных регулярных выражений с использованием ConcurrentHashMap.
     * - Логирование ошибок с помощью Logger для улучшения диагностики.
     */

    private static final Logger logger = Logger.getLogger(RegexMatcher.class.getName());
    private static final Map<String, Pattern> patternCache = new ConcurrentHashMap<>();

    public static boolean matches(String regex, String text) {
        if (regex == null || text == null) {
            logger.warning("Получен null аргумент при вызове matches()");
            throw new InvalidRegexArgumentException("Аргументы regex и text не должны быть null");
        }

        try {
            Pattern pattern = patternCache.computeIfAbsent(regex, Pattern::compile);
            return pattern.matcher(text).matches();
        } catch (PatternSyntaxException e) {
            logger.severe("Ошибка синтаксиса регулярного выражения: " + regex);
            throw new InvalidRegexArgumentException("Некорректное регулярное выражение", e);
        }
    }
}

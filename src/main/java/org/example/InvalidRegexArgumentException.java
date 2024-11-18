package org.example;

/**
 * Исключение, выбрасываемое при неверных аргументах метода проверки регулярных выражений.
 */
public class InvalidRegexArgumentException extends RuntimeException {
    // Конструктор с одним параметром
    public InvalidRegexArgumentException(String message) {
        super(message);
    }

    // Конструктор с двумя параметрами (для случаев, когда есть исключение, которое нужно передать)
    public InvalidRegexArgumentException(String message, Throwable cause) {
        super(message, cause);
    }
}

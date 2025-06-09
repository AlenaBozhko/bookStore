package bookstore.org.bookstore.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Статус сущности
 */
@Getter
@AllArgsConstructor
public enum Genre {
    FANTASY("Фантастика"),
    DETECTIVE("Детектив"),
    NOVEL("Роман"),
    HORROR("Ужасы"),
    RUSSIAN_CLASSICAL("Русская классика");

    private String description;


}

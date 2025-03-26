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
    HORROR("Ужасы");

    private String description;


}

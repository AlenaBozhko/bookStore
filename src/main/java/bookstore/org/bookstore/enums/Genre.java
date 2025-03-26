package bookstore.org.bookstore.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor

public enum Genre {
    FANTASY("Фантастика"),
    DETECTIVE("Детектив"),
    NOVEL("Роман"),
    HORROR("Ужасы");

    private String description;


}

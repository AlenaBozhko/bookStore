package bookstore.org.bookstore.entity;

import bookstore.org.bookstore.enums.Genre;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Entity
@NoArgsConstructor
@Table(name = "books")
public class Book extends BaseEntity {

    /**
     * Название книги
     */
    private String title;

    /**
     * Автор книги
     */
    private String author;

    /**
     * Жанр книги
     */
    private Genre genre;

    /**
     * Описание книги
     */
    private String description;

    /**
     * Цена книги
     */
    private Long price;

    /**
     * Валюта
     */
    private String currency;
}

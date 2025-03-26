package bookstore.org.bookstore.entity;

import bookstore.org.bookstore.enums.Genre;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.antlr.v4.runtime.misc.NotNull;

/**
 * Сущность "Книга"
 */
@Data
@Accessors(chain = true)
@Entity
@NoArgsConstructor
@Table(name = "books")
public class Book extends BaseEntity {

    /**
     * Название книги
     */
    @Column(name = "title", nullable = false, unique = true)
    private String title;

    /**
     * Автор книги
     */
    @Column(name = "author", nullable = false)
    private String author;

    /**
     * Жанр книги
     */
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "genre", nullable = false)
    private Genre genre;

    /**
     * Описание книги
     */
    @Column(name = "description", nullable = false)
    private String description;

    /**
     * Цена книги (у.е.)
     */
    @Column(name = "price", nullable = false)
    private Long price;

    /**
     * Валюта
     */
    @Column(name = "currency", nullable = false)
    private String currency;
}

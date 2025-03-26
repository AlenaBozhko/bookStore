package bookstore.org.bookstore.dto;

import bookstore.org.bookstore.entity.Book;
import bookstore.org.bookstore.enums.Genre;
import com.fasterxml.jackson.annotation.JsonAutoDetect;

/**
 * Объект передачи данных книги
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public record BookDto(

    String title,
    Genre genre,
    String author,
    String description,
    Long price,
    String currency

) {
    /**
     * Сформировать DTO из данных книги
     *
     * @param book книга
     * @return DTO из данных книги
     */
    public static BookDto from(Book book) {
        if (book == null) {
            throw new NullPointerException("Book DTO cannot be created from null book");
        }
        return new BookDto(
            book.getTitle(),
            book.getGenre(),
            book.getAuthor(),
            book.getDescription(),
            book.getPrice(),
            book.getCurrency()
        );

    }

    /**
     * Сформировать сущность книги из текущего DTO
     *
     * @return сущность книги из текущего DTO
     */
    public Book toBook() {
        return new Book()
            .setTitle(title)
            .setGenre(genre)
            .setAuthor(author)
            .setDescription(description)
            .setPrice(price)
            .setCurrency(currency);
    }

}
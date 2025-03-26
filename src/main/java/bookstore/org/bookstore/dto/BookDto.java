package bookstore.org.bookstore.dto;

import bookstore.org.bookstore.entity.Book;
import bookstore.org.bookstore.enums.Genre;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * Объект передачи данных книги
 */
@Schema(description = "Данные книги")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public record BookDto(

    @Schema(description = "ID", example = "1") Long id,
    @Schema(description = "Название", example = "Война и Мир")
    @NotBlank(message = "Введите наименование книги")
    @Size(min = 2, max = 100, message = "Минимальная длинна поля - 2 символа, максимальная - 100")
    String title,

    @Schema(description = "Жанр", example = "NOVEL")
    @NotNull(message = "Укажите жанр")
    Genre genre,

    @Schema(description = "ФИО автора", example = "Толстой Лев Николаевич")
    @NotNull(message = "Укажите ФИО автора")
    @Size(min = 4, max = 100, message = "Минимальная длинна поля - 4 символа, максимальная - 100")
    String author,

    @Schema(description = "Краткое описание книги", example = "В романе рассказывается о предвоенных, военных и послевоенных событиях начала XIX века. Центральной темой является война 1812 года Наполеона с Россией.")
    @NotNull(message = "Укажите краткое описание книги")
    @Size(min = 4, max = 1000, message = "Минимальная длинна поля - 4 символа, максимальная - 100")
    String description,

    @Schema(description = "Цена", example = "2999")
    @NotNull(message = "Укажите цену книги")
    @Pattern(regexp = "^(?!0)\\d{1,5}$")   // Регулярное выражение для проверки ввода числа от 1 до 99999
    Long price,

    @Schema(description = "Валюта", example = "RUB")
    @NotNull(message = "Укажите валюту")
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
            book.getId(),
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
package bookstore.org.bookstore.controller;

import bookstore.org.bookstore.dto.BookDto;
import bookstore.org.bookstore.entity.Book;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

/**
 * API управления книгами
 */
@RequestMapping("/api/v1/book-management")
public interface BookManagementResource {

    /**
     * Создать новую книгу
     *
     * @param bookDto DTO новой книги
     * @return новая книга
     */
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/book")
    ResponseEntity<BookDto> createBook(@RequestBody BookDto bookDto);

    /**
     * Получить книгу по id
     *
     * @param bookId id книги
     * @return найденная книга
     */
    @GetMapping("/book/{bookId}")
    ResponseEntity<BookDto> bookById(@PathVariable("bookId") Long bookId);

    /**
     * Получить книгу по наименованию
     *
     * @param title наименование книги
     * @return найденная книга
     */
    @GetMapping("/book/byTitle/{title}")
    ResponseEntity<BookDto> bookByTitle(@PathVariable("title") String title);

    /**
     * Редактировать книгу по id
     *
     * @param bookId id книги
     * @param update ДТО книги
     * @return отредактированная книга
     */
    @PutMapping("/book/{bookId}")
    ResponseEntity<BookDto> editBook(@PathVariable("bookId") Long bookId,
                                     @RequestBody  BookDto update);


    /**
     * Удалить книгу по id
     *
     * @param bookId id роли
     * @return ответ с кодом
     */
    @DeleteMapping("/book/{bookId}")
    ResponseEntity<?> deleteBook(@PathVariable("bookId") Long bookId);

    /**
     * Получить список книг по автору
     *
     * @param author книга
     * @return List<Book> список книг
     */
    @GetMapping("/books/{author}")
            ResponseEntity<List<BookDto>> getBooksByAuthor(@PathVariable String author);

}

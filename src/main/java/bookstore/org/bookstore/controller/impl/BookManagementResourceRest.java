package bookstore.org.bookstore.controller.impl;

import bookstore.org.bookstore.controller.BookManagementResource;
import bookstore.org.bookstore.dto.BookDto;
import bookstore.org.bookstore.entity.Book;
import bookstore.org.bookstore.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Optional;


/**
 * REST-контроллер управления книгами
 */
@Slf4j
@RestController
public class BookManagementResourceRest implements BookManagementResource {

    private final BookService bookService;

    /**
     * @param bookService Интерфейс управления книгами
     */
    public BookManagementResourceRest(BookService bookService) {
        this.bookService = bookService;
    }

    /**
     * Создать новую книгу
     *
     * @param bookDto сущность регистрации новой книги
     * @return новая книга
     */
    @Override
    public ResponseEntity<BookDto> createBook(BookDto bookDto) {
        log.info("Received request for registry new book");
        Book registeredBook = bookService.createBook(bookDto.toBook());
        URI bookLocation = URI.create(
                ServletUriComponentsBuilder.fromCurrentContextPath()
                        .path("/api/v1/book-management/book/{bookId}")
                        .build(Map.of("bookId", registeredBook.getId()))
                        .toString()
        );
        return ResponseEntity.created(bookLocation).body(BookDto.from(registeredBook));
    }

    /**
     * Получить книгу по id
     *
     * @param bookId id книги
     * @return ДТО книги
     */
    @Override
    public ResponseEntity<BookDto> bookById(Long bookId) {
        log.debug("Getting book by ID: {}", bookId);

        return bookService.getById(bookId)
                .map(BookDto::from)
                .map(ResponseEntity::ok)
                .orElseGet(
                        () -> ResponseEntity.notFound().build()
                );
    }

    /**
     * Получить книгу по названию
     *
     * @param title наименование книги
     * @return ДТО книги
     */
    @Override
    public ResponseEntity<BookDto> bookByTitle(String title) {
        log.debug("Getting book by title: {}", title);

        return bookService.getByTitle(title)
                .map(BookDto::from)
                .map(ResponseEntity::ok)
                .orElseGet(
                        () -> ResponseEntity.notFound().build()
                );
    }

    @Override
    public ResponseEntity<BookDto> editBook(Long bookId, BookDto update) {
        log.info("Edit book by id: {}", bookId);
        Book book = update.toBook();
        book.setId(bookId);
        book = bookService.update(book);
        return ResponseEntity.ok(BookDto.from(book));
    }

    @Override
    public ResponseEntity<?> deleteBook(Long bookId) {
        log.info("Delete book by id: {}", bookId);
        bookService.deleteBookById(bookId);
        return ResponseEntity.noContent().build();
    }


    @Override
    public ResponseEntity<List<BookDto>> getBooksByAuthor(String author) {
        log.debug("Getting books by author: {}", author);
        List<Book> books = bookService.getBooksByAuthor(author);

        return Optional.of(books)
                .filter(b -> !b.isEmpty()) //проверяет, пустой ли список. Если пустой, то сразу попадаем в метод .orElseGet
                .map(b -> b.stream().map(BookDto::from).toList()) //преобразуем List<BookDto> в поток, чтобы потом сопоставить с Books и вернуть список книг
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}

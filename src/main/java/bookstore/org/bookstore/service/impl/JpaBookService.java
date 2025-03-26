package bookstore.org.bookstore.service.impl;

import bookstore.org.bookstore.entity.Book;
import bookstore.org.bookstore.enums.Status;
import bookstore.org.bookstore.exceptions.EntityAlreadyExist;
import bookstore.org.bookstore.exceptions.NonExistEntity;
import bookstore.org.bookstore.repository.BookRepository;
import bookstore.org.bookstore.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * JPA-Реализация сервиса книг для работы с БД
 */
@Slf4j
@Service
public class JpaBookService implements BookService {

    /**
     * Репозиторий книг
     */
    private final BookRepository bookRepository;

    public JpaBookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    /**
     * Создать книгу
     *
     * @param book книга
     * @return книга
     */
    @Override
    public Book createBook(Book book) {
        log.debug("IN createBook -> creating book: {}", book);
        var byTitle = getByTitle(book.getTitle());
        if (byTitle.isPresent()) {
            log.error("IN createBook -> was attempt creating book with title {}, but: already exist", book.getTitle());
            throw new EntityAlreadyExist("Book with title " + book.getTitle() + " already exist");
        }
        if (book.getStatus() == null) {
            book.setStatus(Status.UNCONFIRMED);
        }
        book = bookRepository.saveAndFlush(book);
        return book;
    }

    /**
     * Получить книгу по id
     *
     * @param id идентификатор книги
     * @return книга
     */
    @Override
    public Optional<Book> getById(long id) {
        log.debug("IN getById -> loading book with id: {}", id);
        var book = bookRepository.findById(id);
        if (book.isPresent()) {
            log.info("IN getById -> loaded book with id: {}", id);
        } else {
            log.info("IN getById -> book with id {} not found", id);
        }
        return book;
    }

    /**
     * Получить книгу по названию
     *
     * @param title название книги
     * @return книга
     */
    @Override
    public Optional<Book> getByTitle(String title) {
        log.debug("IN getByTitle -> loading book with title: {}", title);
        var book = bookRepository.findBookByTitle(title);
        if (book.isPresent()) {
            log.info("IN getByTitle -> loaded book with title: {}", title);
        } else {
            log.info("IN getByTitle -> user book title {} not found", title);
        }
        return book;
    }

    @Override
    public Book update(Book book) {
        log.debug("IN update -> creating book: {}", book);
        var byId = getById(book.getId());
        if (byId.isEmpty()) {
            log.error("IN update -> was attempt creating book with id {}, but: not exist", book.getId());
            throw new NonExistEntity("Book with id " + book.getId() + " not exist");
        }
        book = bookRepository.saveAndFlush(merge(byId.get(), book));
        log.info("IN update -> Book with id {} successfully updated", book.getId());

        return book;
    }

    /**
     * Обновить атрибуты книги
     *
     * @param book   книга со старыми атрибутами
     * @param update книга с новыми атрибутами
     * @return книга
     */
    private Book merge(Book book, Book update) {
        if (update.getTitle() != null) {
            book.setTitle(update.getTitle());
        }

        if (update.getGenre() != null) {
            book.setGenre(update.getGenre());
        }

        if (update.getAuthor() != null) {
            book.setAuthor(update.getAuthor());
        }

        if (update.getDescription() != null) {
            book.setDescription(update.getDescription());
        }

        if (update.getPrice() != null) {
            book.setPrice(update.getPrice());
        }

        if (update.getCurrency() != null) {
            book.setCurrency(update.getCurrency());
        }

        return book;
    }


    /**
     * Удалить книгу по id
     *
     * @param id идентификатор книги
     */
    @Override
    public void deleteBookById(long id) {
        log.debug("IN deleteBookById -> delete book with id: {}", id);
        var exist = bookRepository.findById(id);
        if (exist.isPresent()) {
            var book = exist.get();
            book.setStatus(Status.DELETED);
            book = bookRepository.saveAndFlush(book);
            log.debug("Book with id {} set status: {}", book.getId(), book.getStatus());
            log.info("IN deleteBookById -> successfully deleted book with id: {}", id);
        } else {
            log.error("IN deleteBookById -> was attempt deleted book with id {}, but: not exist", id);
            throw new NonExistEntity("Book with id " + id + " not exist");
        }
    }


}

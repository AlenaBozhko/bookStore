package bookstore.org.bookstore.service;

import bookstore.org.bookstore.entity.Book;

import java.util.Optional;

/**
 * Интерфейс управления книгами
 */
public interface BookService {

    /**
     * Создать книгу
     *
     * @param book книга
     * @return книга
     */
    Book createBook(Book book);

    /**
     * Получить книгу по id
     *
     * @param id идентификатор книги
     * @return книга
     */
    Optional<Book> getById(long id);

    /**
     * Получить книгу по названию
     *
     * @param title название книги
     * @return книга
     */
    Optional<Book> getByTitle(String title);


    /**
     * Редактировать книгу
     *
     * @param book книга
     * @return отредактированная книга
     */
    Book update(Book book);


    /**
     * Удалить книгу по id
     *
     * @param id идентификатор книги
     */
    void deleteBookById(long id);
}

package bookstore.org.bookstore.repository;

import bookstore.org.bookstore.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Репозиторий книг
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findById(Long id);

    Optional<Book> findBookByTitle(String title);

    List<Book> findBooksByAuthor(String author);

}
package bookstore.org.bookstore.exceptions;

/**
 * Исключение: сущности не существует
 */
public class NonExistEntity extends RuntimeException {

    /**
     * Конструктор исключения отсутствия существования сущности
     *
     * @param message сообщение
     */
    public NonExistEntity(String message) {
        super(message);
    }
}

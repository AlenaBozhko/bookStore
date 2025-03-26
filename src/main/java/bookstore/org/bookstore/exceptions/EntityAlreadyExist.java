package bookstore.org.bookstore.exceptions;

/**
 * Исключение: сущность уже существует
 */
public class EntityAlreadyExist extends  RuntimeException{


    /**
     * Конструктор исключения: сущность уже создана
     *
     * @param message сообщение
     */
    public EntityAlreadyExist(String message) {
        super(message);
    }
}

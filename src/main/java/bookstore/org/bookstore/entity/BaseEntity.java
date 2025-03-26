package bookstore.org.bookstore.entity;

import bookstore.org.bookstore.enums.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDateTime;
/**
 * Базовая сущность. Поля данного класса будут включены в таблицы всех сущностей, наследующих этот класс
 */
@Data
@MappedSuperclass
public class BaseEntity {

    /**
     * id сущности
     */
    @Schema(hidden = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Дата создания сущности
     */
    @Schema(hidden = true)
    @NotNull
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "created")
    private LocalDateTime created;

    /**
     * Дата обновления сущности
     */
    @Schema(hidden = true)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "updated")
    private LocalDateTime updated;

    /**
     * Статус сущности
     */
    @Schema(hidden = true)
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    /**
     * Предобработка сущности перед сохранением в БД
     */
    @PrePersist
    void onCreate() {
        if (this.created == null) {
            this.created = LocalDateTime.now();
        }

        if (this.status == null) {
            this.status = Status.ACTIVE;
        }
    }

    /**
     * Предобработка сущности перед обновлением в БД
     */
    @PreUpdate
    void onUpdate() {
        this.updated = LocalDateTime.now();
    }
}

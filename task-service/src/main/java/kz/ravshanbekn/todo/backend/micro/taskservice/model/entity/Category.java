package kz.ravshanbekn.todo.backend.micro.taskservice.model.entity;

import jakarta.persistence.Cacheable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;


@Entity
@Table(name = "category_data")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    // т.к. это поле высчитывается автоматически в триггерах - вручную его не обновляем (updatable = false)
    @Column(name = "completed_count", updatable = false)
    private Long completedCount;

    // т.к. это поле высчитывается автоматически в триггерах - вручную его не обновляем (updatable = false)
    @Column(name = "uncompleted_count", updatable = false)
    private Long uncompletedCount;

    @Column(name = "user_id")
    private Long userId;
}


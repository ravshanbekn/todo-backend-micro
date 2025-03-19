package kz.ravshanbekn.todo.backend.micro.taskservice.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "stat_data")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class Stat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "completed_total", updatable = false)
    private Long completedTotal; // значение задается в триггере в БД

    @Column(name = "uncompleted_total", updatable = false)
    private Long uncompletedTotal; // значение задается в триггере в БД

    @Column(name = "user_id")
    private Long userId;
}


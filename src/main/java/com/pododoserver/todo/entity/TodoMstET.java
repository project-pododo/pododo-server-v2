package com.pododoserver.todo.entity;

import com.pododoserver.account.entity.AccountET;
import com.pododoserver.common.entity.BaseET;
import com.pododoserver.todo.constant.TodoStatus;
import com.pododoserver.todo.constant.UseYn;
import com.pododoserver.todo.dto.TodoMstDto;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;

import java.time.LocalDateTime;


@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
@Entity
@Where(clause = "delete_yn = 'N'")
@Table(name = "todo_mst")
public class TodoMstET extends BaseET {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long todoMstId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_mst_id")
    private AccountET accountET;

    @Enumerated(EnumType.STRING)
    @Column(name = "todo_status", length = 4, nullable = false)
    private TodoStatus todoStatus;

    @Column(name = "todo_name", length = 1000)
    private String todoName;

    @Column(name = "todo_detail", length = 5000)
    private String todoDetail;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "use_yn", length = 1, nullable = false)
    private UseYn useYn;

    public TodoMstET updateAllInfo(TodoMstDto dto) {
        this.todoName = dto.getTodoName();
        this.todoDetail = dto.getTodoDetail();
        this.startDate = dto.getStartDate();
        this.endDate = dto.getEndDate();
        return this;
    }

    public TodoMstET updateStatus(TodoStatus todoStatus) {
        this.todoStatus = todoStatus.equals(TodoStatus.DONE) ? TodoStatus.WAIT : TodoStatus.DONE;
        return this;
    }

    public TodoMstET delete() {
        this.useYn = UseYn.N;
        return this;
    }

    public TodoMstET restore() {
        this.useYn = UseYn.Y;
        return this;
    }
}

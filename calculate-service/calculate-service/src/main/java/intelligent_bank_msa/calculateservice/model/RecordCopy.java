package intelligent_bank_msa.calculateservice.model;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.time.Month;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class RecordCopy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String bankBookNum;

    private long money;

    @Enumerated(value = EnumType.STRING)
    private RecordState recordState;

    @Column(updatable = false)
    private int createdYear;

    @Enumerated(value = EnumType.STRING)
    @Column(updatable = false)
    private Month createdMonth;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdDate;

    @Builder
    public RecordCopy(Long id, String title, String bankBookNum, long money, RecordState recordState, int createdYear, Month createdMonth) {
        this.id = id;
        this.title = title;
        this.bankBookNum = bankBookNum;
        this.money = money;
        this.recordState = recordState;
        this.createdYear = createdYear;
        this.createdMonth = createdMonth;
    }
}

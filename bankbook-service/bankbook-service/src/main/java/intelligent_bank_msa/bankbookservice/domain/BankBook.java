package intelligent_bank_msa.bankbookservice.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class BankBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String bankBookNum;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    private long balance;

    @Enumerated(value = EnumType.STRING)
    private BankBookState bankBookState;

    @CreatedDate
    @Column(updatable = false)
    private LocalDate createdDate;

    @Builder
    public BankBook(Long id, String bankBookNum, long balance, BankBookState bankBookState, String email, String password) {
        this.id = id;
        this.bankBookNum = bankBookNum;
        this.balance = balance;
        this.bankBookState = bankBookState;
        this.email = email;
        this.password = password;
    }
}

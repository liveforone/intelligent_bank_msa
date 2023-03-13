package intelligent_bank_msa.bankbookservice.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBankBook is a Querydsl query type for BankBook
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBankBook extends EntityPathBase<BankBook> {

    private static final long serialVersionUID = -1139014365L;

    public static final QBankBook bankBook = new QBankBook("bankBook");

    public final NumberPath<Long> balance = createNumber("balance", Long.class);

    public final StringPath bankBookNum = createString("bankBookNum");

    public final EnumPath<BankBookState> bankBookState = createEnum("bankBookState", BankBookState.class);

    public final DatePath<java.time.LocalDate> createdDate = createDate("createdDate", java.time.LocalDate.class);

    public final StringPath email = createString("email");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath password = createString("password");

    public QBankBook(String variable) {
        super(BankBook.class, forVariable(variable));
    }

    public QBankBook(Path<? extends BankBook> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBankBook(PathMetadata metadata) {
        super(BankBook.class, metadata);
    }

}


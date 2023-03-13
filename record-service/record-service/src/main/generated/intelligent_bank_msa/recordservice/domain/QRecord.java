package intelligent_bank_msa.recordservice.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QRecord is a Querydsl query type for Record
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRecord extends EntityPathBase<Record> {

    private static final long serialVersionUID = -1158180733L;

    public static final QRecord record = new QRecord("record");

    public final StringPath bankBookNum = createString("bankBookNum");

    public final DateTimePath<java.time.LocalDateTime> createdDate = createDateTime("createdDate", java.time.LocalDateTime.class);

    public final EnumPath<java.time.Month> createdMonth = createEnum("createdMonth", java.time.Month.class);

    public final NumberPath<Integer> createdYear = createNumber("createdYear", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> money = createNumber("money", Long.class);

    public final EnumPath<RecordState> recordState = createEnum("recordState", RecordState.class);

    public final StringPath title = createString("title");

    public QRecord(String variable) {
        super(Record.class, forVariable(variable));
    }

    public QRecord(Path<? extends Record> path) {
        super(path.getType(), path.getMetadata());
    }

    public QRecord(PathMetadata metadata) {
        super(Record.class, metadata);
    }

}


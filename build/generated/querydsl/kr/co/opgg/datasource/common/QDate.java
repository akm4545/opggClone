package kr.co.opgg.datasource.common;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QDate is a Querydsl query type for Date
 */
@Generated("com.querydsl.codegen.DefaultSupertypeSerializer")
public class QDate extends EntityPathBase<Date> {

    private static final long serialVersionUID = -80789830L;

    public static final QDate date = new QDate("date");

    public final DateTimePath<java.time.LocalDateTime> createDate = createDateTime("createDate", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> deleteDate = createDateTime("deleteDate", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> updateDate = createDateTime("updateDate", java.time.LocalDateTime.class);

    public QDate(String variable) {
        super(Date.class, forVariable(variable));
    }

    public QDate(Path<? extends Date> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDate(PathMetadata metadata) {
        super(Date.class, metadata);
    }

}


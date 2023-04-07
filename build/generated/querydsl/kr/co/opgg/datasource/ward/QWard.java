package kr.co.opgg.datasource.ward;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QWard is a Querydsl query type for Ward
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QWard extends EntityPathBase<Ward> {

    private static final long serialVersionUID = 2100218327L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QWard ward = new QWard("ward");

    public final kr.co.opgg.datasource.common.QDate _super = new kr.co.opgg.datasource.common.QDate(this);

    public final kr.co.opgg.datasource.board.QBoard board;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createDate = _super.createDate;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> deleteDate = _super.deleteDate;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updateDate = _super.updateDate;

    public final kr.co.opgg.datasource.user.QUser user;

    public final NumberPath<Integer> wardIdx = createNumber("wardIdx", Integer.class);

    public QWard(String variable) {
        this(Ward.class, forVariable(variable), INITS);
    }

    public QWard(Path<? extends Ward> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QWard(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QWard(PathMetadata metadata, PathInits inits) {
        this(Ward.class, metadata, inits);
    }

    public QWard(Class<? extends Ward> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.board = inits.isInitialized("board") ? new kr.co.opgg.datasource.board.QBoard(forProperty("board"), inits.get("board")) : null;
        this.user = inits.isInitialized("user") ? new kr.co.opgg.datasource.user.QUser(forProperty("user"), inits.get("user")) : null;
    }

}


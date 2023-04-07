package kr.co.opgg.datasource.qna;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QQNA is a Querydsl query type for QNA
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QQNA extends EntityPathBase<QNA> {

    private static final long serialVersionUID = 1749378051L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QQNA qNA = new QQNA("qNA");

    public final kr.co.opgg.datasource.common.QDate _super = new kr.co.opgg.datasource.common.QDate(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createDate = _super.createDate;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> deleteDate = _super.deleteDate;

    public final StringPath qnaContent = createString("qnaContent");

    public final NumberPath<Integer> qnaIdx = createNumber("qnaIdx", Integer.class);

    public final StringPath qnaTitle = createString("qnaTitle");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updateDate = _super.updateDate;

    public final kr.co.opgg.datasource.user.QUser user;

    public QQNA(String variable) {
        this(QNA.class, forVariable(variable), INITS);
    }

    public QQNA(Path<? extends QNA> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QQNA(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QQNA(PathMetadata metadata, PathInits inits) {
        this(QNA.class, metadata, inits);
    }

    public QQNA(Class<? extends QNA> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new kr.co.opgg.datasource.user.QUser(forProperty("user"), inits.get("user")) : null;
    }

}


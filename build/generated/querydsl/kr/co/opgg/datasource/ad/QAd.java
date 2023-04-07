package kr.co.opgg.datasource.ad;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAd is a Querydsl query type for Ad
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAd extends EntityPathBase<Ad> {

    private static final long serialVersionUID = 1720774007L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAd ad = new QAd("ad");

    public final kr.co.opgg.datasource.common.QDate _super = new kr.co.opgg.datasource.common.QDate(this);

    public final NumberPath<Long> adIdx = createNumber("adIdx", Long.class);

    public final StringPath adLink = createString("adLink");

    public final StringPath adTitle = createString("adTitle");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createDate = _super.createDate;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> deleteDate = _super.deleteDate;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updateDate = _super.updateDate;

    public final kr.co.opgg.datasource.user.QUser user;

    public QAd(String variable) {
        this(Ad.class, forVariable(variable), INITS);
    }

    public QAd(Path<? extends Ad> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAd(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAd(PathMetadata metadata, PathInits inits) {
        this(Ad.class, metadata, inits);
    }

    public QAd(Class<? extends Ad> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new kr.co.opgg.datasource.user.QUser(forProperty("user"), inits.get("user")) : null;
    }

}


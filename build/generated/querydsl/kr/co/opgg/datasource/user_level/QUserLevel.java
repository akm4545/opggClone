package kr.co.opgg.datasource.user_level;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserLevel is a Querydsl query type for UserLevel
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserLevel extends EntityPathBase<UserLevel> {

    private static final long serialVersionUID = 891202898L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserLevel userLevel = new QUserLevel("userLevel");

    public final kr.co.opgg.datasource.common.QDate _super = new kr.co.opgg.datasource.common.QDate(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createDate = _super.createDate;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> deleteDate = _super.deleteDate;

    public final kr.co.opgg.datasource.level.QLevel level;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updateDate = _super.updateDate;

    public final kr.co.opgg.datasource.user.QUser user;

    public final NumberPath<Integer> userLevelExp = createNumber("userLevelExp", Integer.class);

    public final NumberPath<Integer> userLevelIdx = createNumber("userLevelIdx", Integer.class);

    public QUserLevel(String variable) {
        this(UserLevel.class, forVariable(variable), INITS);
    }

    public QUserLevel(Path<? extends UserLevel> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserLevel(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserLevel(PathMetadata metadata, PathInits inits) {
        this(UserLevel.class, metadata, inits);
    }

    public QUserLevel(Class<? extends UserLevel> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.level = inits.isInitialized("level") ? new kr.co.opgg.datasource.level.QLevel(forProperty("level")) : null;
        this.user = inits.isInitialized("user") ? new kr.co.opgg.datasource.user.QUser(forProperty("user"), inits.get("user")) : null;
    }

}


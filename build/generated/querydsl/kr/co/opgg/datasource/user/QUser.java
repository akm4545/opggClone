package kr.co.opgg.datasource.user;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = 179127095L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUser user = new QUser("user");

    public final kr.co.opgg.datasource.common.QDate _super = new kr.co.opgg.datasource.common.QDate(this);

    public final ListPath<kr.co.opgg.datasource.ad.Ad, kr.co.opgg.datasource.ad.QAd> ads = this.<kr.co.opgg.datasource.ad.Ad, kr.co.opgg.datasource.ad.QAd>createList("ads", kr.co.opgg.datasource.ad.Ad.class, kr.co.opgg.datasource.ad.QAd.class, PathInits.DIRECT2);

    public final kr.co.opgg.datasource.authority.QAuthority authority;

    public final ListPath<kr.co.opgg.datasource.board.Board, kr.co.opgg.datasource.board.QBoard> boards = this.<kr.co.opgg.datasource.board.Board, kr.co.opgg.datasource.board.QBoard>createList("boards", kr.co.opgg.datasource.board.Board.class, kr.co.opgg.datasource.board.QBoard.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createDate = _super.createDate;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> deleteDate = _super.deleteDate;

    public final ListPath<kr.co.opgg.datasource.qna.QNA, kr.co.opgg.datasource.qna.QQNA> qnas = this.<kr.co.opgg.datasource.qna.QNA, kr.co.opgg.datasource.qna.QQNA>createList("qnas", kr.co.opgg.datasource.qna.QNA.class, kr.co.opgg.datasource.qna.QQNA.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updateDate = _super.updateDate;

    public final StringPath userId = createString("userId");

    public final NumberPath<Long> userIdx = createNumber("userIdx", Long.class);

    public final kr.co.opgg.datasource.user_level.QUserLevel userLevel;

    public final StringPath userNickName = createString("userNickName");

    public final ListPath<kr.co.opgg.datasource.user_policy.UserPolicy, kr.co.opgg.datasource.user_policy.QUserPolicy> userPolicies = this.<kr.co.opgg.datasource.user_policy.UserPolicy, kr.co.opgg.datasource.user_policy.QUserPolicy>createList("userPolicies", kr.co.opgg.datasource.user_policy.UserPolicy.class, kr.co.opgg.datasource.user_policy.QUserPolicy.class, PathInits.DIRECT2);

    public final StringPath userPolicyYn = createString("userPolicyYn");

    public final StringPath userPw = createString("userPw");

    public QUser(String variable) {
        this(User.class, forVariable(variable), INITS);
    }

    public QUser(Path<? extends User> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUser(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUser(PathMetadata metadata, PathInits inits) {
        this(User.class, metadata, inits);
    }

    public QUser(Class<? extends User> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.authority = inits.isInitialized("authority") ? new kr.co.opgg.datasource.authority.QAuthority(forProperty("authority"), inits.get("authority")) : null;
        this.userLevel = inits.isInitialized("userLevel") ? new kr.co.opgg.datasource.user_level.QUserLevel(forProperty("userLevel"), inits.get("userLevel")) : null;
    }

}


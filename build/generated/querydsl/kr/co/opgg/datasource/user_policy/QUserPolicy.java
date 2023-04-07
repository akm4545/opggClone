package kr.co.opgg.datasource.user_policy;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserPolicy is a Querydsl query type for UserPolicy
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserPolicy extends EntityPathBase<UserPolicy> {

    private static final long serialVersionUID = -1716790436L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserPolicy userPolicy = new QUserPolicy("userPolicy");

    public final kr.co.opgg.datasource.common.QDate _super = new kr.co.opgg.datasource.common.QDate(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createDate = _super.createDate;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> deleteDate = _super.deleteDate;

    public final kr.co.opgg.datasource.policy.QPolicy policy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updateDate = _super.updateDate;

    public final NumberPath<Integer> userIdx = createNumber("userIdx", Integer.class);

    public final NumberPath<Integer> userPolicyIdx = createNumber("userPolicyIdx", Integer.class);

    public QUserPolicy(String variable) {
        this(UserPolicy.class, forVariable(variable), INITS);
    }

    public QUserPolicy(Path<? extends UserPolicy> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserPolicy(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserPolicy(PathMetadata metadata, PathInits inits) {
        this(UserPolicy.class, metadata, inits);
    }

    public QUserPolicy(Class<? extends UserPolicy> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.policy = inits.isInitialized("policy") ? new kr.co.opgg.datasource.policy.QPolicy(forProperty("policy")) : null;
    }

}


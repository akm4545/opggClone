package kr.co.opgg.datasource.level_policy;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QLevelPolicy is a Querydsl query type for LevelPolicy
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLevelPolicy extends EntityPathBase<LevelPolicy> {

    private static final long serialVersionUID = 1956681068L;

    public static final QLevelPolicy levelPolicy = new QLevelPolicy("levelPolicy");

    public final kr.co.opgg.datasource.common.QDate _super = new kr.co.opgg.datasource.common.QDate(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createDate = _super.createDate;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> deleteDate = _super.deleteDate;

    public final NumberPath<Integer> levelPolicyExp = createNumber("levelPolicyExp", Integer.class);

    public final NumberPath<Integer> levelPolicyIdx = createNumber("levelPolicyIdx", Integer.class);

    public final StringPath levelPolicyType = createString("levelPolicyType");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updateDate = _super.updateDate;

    public QLevelPolicy(String variable) {
        super(LevelPolicy.class, forVariable(variable));
    }

    public QLevelPolicy(Path<? extends LevelPolicy> path) {
        super(path.getType(), path.getMetadata());
    }

    public QLevelPolicy(PathMetadata metadata) {
        super(LevelPolicy.class, metadata);
    }

}


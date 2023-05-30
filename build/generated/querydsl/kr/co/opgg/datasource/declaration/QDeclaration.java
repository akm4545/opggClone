package kr.co.opgg.datasource.declaration;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDeclaration is a Querydsl query type for Declaration
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDeclaration extends EntityPathBase<Declaration> {

    private static final long serialVersionUID = -1685631441L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDeclaration declaration = new QDeclaration("declaration");

    public final kr.co.opgg.datasource.common.QDate _super = new kr.co.opgg.datasource.common.QDate(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createDate = _super.createDate;

    public final StringPath declarationContent = createString("declarationContent");

    public final NumberPath<Integer> declarationIdx = createNumber("declarationIdx", Integer.class);

    public final NumberPath<Integer> declarationTargetIdx = createNumber("declarationTargetIdx", Integer.class);

    public final StringPath declarationTargetType = createString("declarationTargetType");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> deleteDate = _super.deleteDate;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updateDate = _super.updateDate;

    public final kr.co.opgg.datasource.user.QUser user;

    public QDeclaration(String variable) {
        this(Declaration.class, forVariable(variable), INITS);
    }

    public QDeclaration(Path<? extends Declaration> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QDeclaration(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QDeclaration(PathMetadata metadata, PathInits inits) {
        this(Declaration.class, metadata, inits);
    }

    public QDeclaration(Class<? extends Declaration> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new kr.co.opgg.datasource.user.QUser(forProperty("user"), inits.get("user")) : null;
    }

}


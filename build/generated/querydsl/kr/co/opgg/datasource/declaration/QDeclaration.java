package kr.co.opgg.datasource.declaration;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QDeclaration is a Querydsl query type for Declaration
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDeclaration extends EntityPathBase<Declaration> {

    private static final long serialVersionUID = -1685631441L;

    public static final QDeclaration declaration = new QDeclaration("declaration");

    public final kr.co.opgg.datasource.common.QDate _super = new kr.co.opgg.datasource.common.QDate(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createDate = _super.createDate;

    public final NumberPath<Integer> declarationIdx = createNumber("declarationIdx", Integer.class);

    public final NumberPath<Integer> declarationTargetIdx = createNumber("declarationTargetIdx", Integer.class);

    public final NumberPath<Integer> declarationTargetType = createNumber("declarationTargetType", Integer.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> deleteDate = _super.deleteDate;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updateDate = _super.updateDate;

    public QDeclaration(String variable) {
        super(Declaration.class, forVariable(variable));
    }

    public QDeclaration(Path<? extends Declaration> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDeclaration(PathMetadata metadata) {
        super(Declaration.class, metadata);
    }

}


package kr.co.opgg.datasource.recommended_log;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QRecommendedLog is a Querydsl query type for RecommendedLog
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRecommendedLog extends EntityPathBase<RecommendedLog> {

    private static final long serialVersionUID = -727083026L;

    public static final QRecommendedLog recommendedLog = new QRecommendedLog("recommendedLog");

    public final kr.co.opgg.datasource.common.QDate _super = new kr.co.opgg.datasource.common.QDate(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createDate = _super.createDate;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> deleteDate = _super.deleteDate;

    public final NumberPath<Integer> recommendedLogIdx = createNumber("recommendedLogIdx", Integer.class);

    public final NumberPath<Integer> targetIdx = createNumber("targetIdx", Integer.class);

    public final StringPath type = createString("type");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updateDate = _super.updateDate;

    public final NumberPath<Integer> userIdx = createNumber("userIdx", Integer.class);

    public QRecommendedLog(String variable) {
        super(RecommendedLog.class, forVariable(variable));
    }

    public QRecommendedLog(Path<? extends RecommendedLog> path) {
        super(path.getType(), path.getMetadata());
    }

    public QRecommendedLog(PathMetadata metadata) {
        super(RecommendedLog.class, metadata);
    }

}


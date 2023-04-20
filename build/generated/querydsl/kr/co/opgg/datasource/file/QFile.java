package kr.co.opgg.datasource.file;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFile is a Querydsl query type for File
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFile extends EntityPathBase<File> {

    private static final long serialVersionUID = -1477137961L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFile file = new QFile("file");

    public final kr.co.opgg.datasource.ad.QAd ad;

    public final kr.co.opgg.datasource.board.QBoard board;

    public final StringPath fileDirectory = createString("fileDirectory");

    public final NumberPath<Long> fileIdx = createNumber("fileIdx", Long.class);

    public final StringPath fileName = createString("fileName");

    public final kr.co.opgg.datasource.qna.QQNA qna;

    public QFile(String variable) {
        this(File.class, forVariable(variable), INITS);
    }

    public QFile(Path<? extends File> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QFile(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QFile(PathMetadata metadata, PathInits inits) {
        this(File.class, metadata, inits);
    }

    public QFile(Class<? extends File> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.ad = inits.isInitialized("ad") ? new kr.co.opgg.datasource.ad.QAd(forProperty("ad"), inits.get("ad")) : null;
        this.board = inits.isInitialized("board") ? new kr.co.opgg.datasource.board.QBoard(forProperty("board"), inits.get("board")) : null;
        this.qna = inits.isInitialized("qna") ? new kr.co.opgg.datasource.qna.QQNA(forProperty("qna"), inits.get("qna")) : null;
    }

}


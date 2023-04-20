package kr.co.opgg.datasource.board;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBoard is a Querydsl query type for Board
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBoard extends EntityPathBase<Board> {

    private static final long serialVersionUID = -930224505L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBoard board = new QBoard("board");

    public final kr.co.opgg.datasource.common.QDate _super = new kr.co.opgg.datasource.common.QDate(this);

    public final NumberPath<Integer> badCount = createNumber("badCount", Integer.class);

    public final NumberPath<Integer> boardGoodCount = createNumber("boardGoodCount", Integer.class);

    public final NumberPath<Integer> boardIdx = createNumber("boardIdx", Integer.class);

    public final StringPath boardType = createString("boardType");

    public final ListPath<kr.co.opgg.datasource.comment.Comment, kr.co.opgg.datasource.comment.QComment> comments = this.<kr.co.opgg.datasource.comment.Comment, kr.co.opgg.datasource.comment.QComment>createList("comments", kr.co.opgg.datasource.comment.Comment.class, kr.co.opgg.datasource.comment.QComment.class, PathInits.DIRECT2);

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createDate = _super.createDate;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> deleteDate = _super.deleteDate;

    public final ListPath<kr.co.opgg.datasource.file.File, kr.co.opgg.datasource.file.QFile> files = this.<kr.co.opgg.datasource.file.File, kr.co.opgg.datasource.file.QFile>createList("files", kr.co.opgg.datasource.file.File.class, kr.co.opgg.datasource.file.QFile.class, PathInits.DIRECT2);

    public final NumberPath<Integer> readCount = createNumber("readCount", Integer.class);

    public final StringPath title = createString("title");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updateDate = _super.updateDate;

    public final kr.co.opgg.datasource.user.QUser user;

    public QBoard(String variable) {
        this(Board.class, forVariable(variable), INITS);
    }

    public QBoard(Path<? extends Board> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBoard(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBoard(PathMetadata metadata, PathInits inits) {
        this(Board.class, metadata, inits);
    }

    public QBoard(Class<? extends Board> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new kr.co.opgg.datasource.user.QUser(forProperty("user"), inits.get("user")) : null;
    }

}


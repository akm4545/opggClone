package kr.co.opgg.datasource.board;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.jpa.sql.JPASQLQuery;
import com.querydsl.sql.*;
import kr.co.opgg.datasource.comment.Comment;
import kr.co.opgg.datasource.comment.QComment;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class BoardQueryDsl {

    @PersistenceContext
    private EntityManager em;

    public List<Comment> selectCommentList(Integer boardIdx){
        JPASQLQuery<?> query = new JPASQLQuery(em, MySQLTemplates.DEFAULT);

        QComment qComment = new QComment("c");
        QComment subQComment = new QComment("subC");

        /*QUser qUser = new QUser("u");
        QUser subQUser = new QUser("subU");

        QUserLevel qUserLevel = new QUserLevel("ul");
        QUserLevel subQUserLevel = new QUserLevel("subQUl");

        QLevel qLevel = new QLevel("l");
        QLevel subQLevel = new QLevel("subQL");*/

        EntityPathBase<QComment> rec = new EntityPathBase<QComment>(QComment.class, "sub");

        SQLQuery<Comment> q = SQLExpressions
                .select(
                        Projections.fields(Comment.class, qComment.commentIdx, qComment.commentParentIdx, qComment.commentContent))
                .from(qComment)
                .where(qComment.board.boardIdx.eq(boardIdx));

        SQLQuery<Comment> q1 = SQLExpressions
                .select(
                        Projections.fields(Comment.class, subQComment.commentIdx, subQComment.commentParentIdx, subQComment.commentContent)
                )
                .from(qComment, rec)
                .where(qComment.commentIdx.eq(subQComment.commentParentIdx));

        Union<Comment> union = SQLExpressions.unionAll(q, q1);

        List<Comment> fetch = query.withRecursive(rec, qComment.commentIdx, qComment.commentParentIdx, qComment.commentContent)
                .as(union)
                .select(
                        Projections.fields(Comment.class, subQComment.commentIdx, subQComment.commentParentIdx, subQComment.commentContent))
                .from(rec)
                .fetch();

        return fetch;
    }
}

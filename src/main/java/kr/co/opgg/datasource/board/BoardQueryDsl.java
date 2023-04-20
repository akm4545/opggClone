package kr.co.opgg.datasource.board;

import com.querydsl.jpa.sql.JPASQLQuery;
import com.querydsl.sql.SQLTemplates;
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

    public List<Comment> selectCommentList(){
        JPASQLQuery query = new JPASQLQuery(em, SQLTemplates.DEFAULT);
        QComment qComment = new QComment("c");

        //query.re

        return null;
    }
}

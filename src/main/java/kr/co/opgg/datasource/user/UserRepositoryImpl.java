package kr.co.opgg.datasource.user;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import static kr.co.opgg.datasource.user.QUser.user;

public class UserRepositoryImpl implements UserRepositoryCustom{

    private JPAQueryFactory query;

    @Override
    public Long findByUserId(String userId) {

        return query.select(user.count())
                .from(user)
                .where(user.userId.eq(userId))
                .fetchFirst();
    }
}

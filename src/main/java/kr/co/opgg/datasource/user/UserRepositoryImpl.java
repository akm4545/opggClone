package kr.co.opgg.datasource.user;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.opgg.apis.user.dto.UserRequest;
import kr.co.opgg.apis.user.dto.UserResponse;

import static kr.co.opgg.datasource.user.QUser.user;

public class UserRepositoryImpl implements UserRepositoryCustom{

    private JPAQueryFactory query;

    @Override
    public UserResponse findByUserId(String userId) {
        return query.select(Projections.fields(UserResponse.class,
                        user.userId))
                .from(user)
                .where(user.userId.eq(userId))
                .fetchFirst();
    }

    @Override
    public UserResponse findUserIdByPhone(UserRequest.UserPrivateRequest userRequest) {
        return query.select(Projections.fields(UserResponse.class,
                        user.userId
                        ,user.userPw))
                .from(user)
                .where(user.userPhone.eq(userRequest.getUserPhone()))
                .fetchFirst();
    }
}

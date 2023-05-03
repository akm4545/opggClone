package kr.co.opgg.datasource.ad;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.opgg.apis.ad.dto.AdResponse;

import java.util.List;


import static kr.co.opgg.datasource.ad.QAd.ad;
public class AdRepositoryImpl implements AdRepositoryCustom{

    private JPAQueryFactory query;

    @Override
    public List<Ad> findByStartDateBetweenEndDate(String toDay) {
//        return query.select(Projections.fields(AdResponse.class,
//                ad.adIdx
//                ,ad.adLink
//                ,ad.adTitle
//                ,ad.user.userIdx))
//                .from(ad)


        return null;
    }
}

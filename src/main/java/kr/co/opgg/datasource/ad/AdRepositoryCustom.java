package kr.co.opgg.datasource.ad;

import java.util.List;

public interface AdRepositoryCustom {
    List<Ad> findByStartDateBetweenEndDate(String toDay);
}

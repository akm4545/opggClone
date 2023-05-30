package kr.co.opgg.datasource.ad;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface AdRepository extends JpaRepository<Ad, Integer>, AdRepositoryCustom {

    List<Ad> findByAdStartDateLessThanEqualAndAdEndDateGreaterThanEqual(Date date, Date date1);
}

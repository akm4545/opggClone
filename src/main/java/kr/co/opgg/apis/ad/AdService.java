package kr.co.opgg.apis.ad;

import kr.co.opgg.apis.ad.dto.AdResponse;
import kr.co.opgg.apis.common.ResponseService;
import kr.co.opgg.apis.common.dto.ListResult;
import kr.co.opgg.datasource.ad.Ad;
import kr.co.opgg.datasource.ad.AdRepository;
import kr.co.opgg.utils.pagination.PageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdService {

    private final PageUtil pageUtil;
    
    private final AdRepository adRepository;

    private final ResponseService responseService;
    public ListResult<AdResponse> findAllAd() {

        List<Ad> adList = adRepository.findByAdStartDateLessThanEqualAndAdEndDateGreaterThanEqual(new Date(), new Date());

        return responseService.getListResult(adList.stream()
                .map(ad -> {
                    AdResponse adResponse = AdResponse.builder()
                            .adIdx(ad.getAdIdx())
                            .adLink(ad.getAdLink())
                            .adTitle(ad.getAdTitle())
                            .userIdx(ad.getUser().getUserIdx())
                            .build();
                    return adResponse;
                })
                .collect(Collectors.toList()));
    }
}

package kr.co.opgg.apis.ad;


import kr.co.opgg.apis.ad.dto.AdResponse;
import kr.co.opgg.apis.common.dto.ListResult;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/ad")
@AllArgsConstructor
public class AdController {

    private final AdService adService;

    @GetMapping("/ads")
    public ResponseEntity<ListResult<AdResponse>> adList() {

        return ResponseEntity.ok(adService.findAllAd());

    }


}

package kr.co.opgg.apis.common;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/common")
public class CommonController {
    private final CommonService commonService;

    /**
     * API 서버 헬스체크
     */
    @GetMapping("/health/check")
    private ResponseEntity healthCheck() {
        return ResponseEntity.ok(commonService.healthCheck());
    }

}

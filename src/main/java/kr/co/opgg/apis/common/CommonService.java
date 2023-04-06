package kr.co.opgg.apis.common;

import kr.co.opgg.apis.common.dto.CommonResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CommonService {
    private final ResponseService responseService;

    public CommonResult healthCheck() {
        return responseService.getSuccessResult();
    }
}

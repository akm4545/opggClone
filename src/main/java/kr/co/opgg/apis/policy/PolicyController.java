package kr.co.opgg.apis.policy;

import kr.co.opgg.apis.policy.dto.PolicyRequest;
import kr.co.opgg.utils.validate.ValidateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/policy")
public class PolicyController {

    @Autowired
    private PolicyService policyService;

    @GetMapping("")
    public ResponseEntity<?> selectPolicyList(@Valid PolicyRequest.PolicyType policyType, BindingResult bindingResult){
        ValidateUtil.validateBindingResult(bindingResult);

        return null;
    }
}

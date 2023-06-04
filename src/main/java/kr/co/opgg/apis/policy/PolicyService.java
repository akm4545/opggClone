package kr.co.opgg.apis.policy;

import kr.co.opgg.datasource.policy.PolicyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PolicyService {

    @Autowired
    private PolicyRepository policyRepository;

}

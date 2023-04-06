package kr.co.opgg.utils.validate;

import kr.co.opgg.common.exception.CommonException;
import org.springframework.validation.BindingResult;

public class ValidateUtil {

    public static void validateBindingResult(BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            CommonException.INVALID_PARAMS_EXCEPTION.setBindingResult(bindingResult);
            throw CommonException.INVALID_PARAMS_EXCEPTION;
        }
    }
}

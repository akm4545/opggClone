package kr.co.opgg.apis.common.dto;

import kr.co.opgg.common.exception.enums.ErrorCodeAndMessage;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Getter
@Setter
public class ExceptionWithErrorResponse extends ExceptionResponse{

    List<HashMap<String, String>> errors = new ArrayList<>();

    public ExceptionWithErrorResponse(ErrorCodeAndMessage errorCodeAndMessage, BindingResult bindingResult){
        super(errorCodeAndMessage);

        List<ObjectError> globalErrors = bindingResult.getGlobalErrors();
        for (ObjectError globalError : globalErrors) {
            HashMap<String, String> errorMap = new HashMap<>();
            errorMap.put("message", globalError.getDefaultMessage());
            errorMap.put("code", globalError.getCode());
            errors.add(errorMap);
        }

        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        for (FieldError fieldError : fieldErrors) {
            HashMap<String, String> errorMap = new HashMap<>();
            errorMap.put("message", fieldError.getDefaultMessage());
            errorMap.put("code", fieldError.getCode());
            errorMap.put("field", fieldError.getField());
            errors.add(errorMap);
        }
    }

}

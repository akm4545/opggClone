package kr.co.opgg.utils.validate;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
public class PageUtil {

    public Pageable getSortPageable(Pageable pageable, String sortTarget){
        Integer pageNumber = pageable.getPageNumber();
        Integer pageSize = pageable.getPageSize();

        if (pageNumber == null || pageNumber < 0) {
            pageNumber = 0;
        } else if (pageNumber != 0) {
            pageNumber -= 1;
        }

        return PageRequest.of(pageNumber, pageSize, Sort.by(sortTarget));
    }
}

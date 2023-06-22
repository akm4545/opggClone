package kr.co.opgg.apis.mulri_search;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/")
public class MultiSearchController {

    private final MultiSearchService multiSearchService;
}

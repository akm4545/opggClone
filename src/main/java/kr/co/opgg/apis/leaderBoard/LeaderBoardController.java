package kr.co.opgg.apis.leaderBoard;

import kr.co.opgg.apis.leaderBoard.dto.LeaderBoardRequest;
import kr.co.opgg.apis.leaderBoard.dto.LeaderBoardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@RestController
@RequestMapping("/leaderBoard")
public class LeaderBoardController {

    @Autowired
    private LeaderBoardService leaderBoardService;

    @Value("lol.leaderboard")
    private String leaderBoardURL;

    @GetMapping("")
    //진입 시기는 캐시에 없을테니까 마지막꺼 가져오기
    public ResponseEntity selectLeaderBoardList(LeaderBoardRequest.SearchLeaderBoardDto searchDto){
        WebClient webClient = leaderBoardService.leaderBoardWebClient(leaderBoardURL);
        Integer page = searchDto.getPage();

        LeaderBoardResponse.SelectLeaderBoardListDto leaderBoardList = leaderBoardService.getApiParams(searchDto);

        for(int i=0; i<page; i++){
            List<LeaderBoardResponse.LeaderBoardItemDto> leaderBoardItemDtoList = (List<LeaderBoardResponse.LeaderBoardItemDto>) webClient.post()//client <- 위에서 만든 객체
                    .uri("/CHALLENGER/I?page=" + page)
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .bodyToMono(LeaderBoardResponse.LeaderBoardItemDto.class)
                    .block();
        }

        //5. 다1 1~10P, 다2 11~15, 다3 16~20 (실제 존재하는 페이지수 - 해당 페이지로 요청 전까지는 파악 불가)
        //6. 17P 조회시 
        //7. 1P ~ 17P까지 조회 실행 티어 - 디비전 순으로 조회 ex -> 다1 1P 100 -> 다1 10P 1000 -> 이후 11P 
        //요청시 null 값이 뜨므로 1P로 돌아가서 다2 1P 조회 반복값은 11이며 요청 Page는 1로 초기화
        //8. 해당 정보를 캐시에 저장 이후 저장된 데이터에 대해 같은 요청값이 들어오면 캐시 데이터 리턴 
        //9. 1달 주기로 캐시 초기화

        return null;
    }
}

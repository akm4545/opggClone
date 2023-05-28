package kr.co.opgg.apis.leaderBoard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/leaderBoard")
public class LeaderBoardController {

    @Autowired
    private LeaderBoardService leaderBoardService;


    ///lol/league-exp/v4/entries/{queue}/{tier}/{division} = url
    @GetMapping("")
    public ResponseEntity selectLeaderBoardList(){
        //1. 페이지 정보를 반환 안해주므로 내부적으로 페이지 처리
        //2. 마찬가지로 모든 유저수를 받는 api 가 없으므로 티어/티비전 별 마지막 페이지를 얻어내 유저수를 추출해야함
        //3. 테스트용 api는 요청 횟수 제한이 있으므로 2번은 폐기
        //4. page는 무한으로 생성시키되 없으면 없다는 알림을 띄우로도록 처리
        //5. 다1 1~10P, 다2 11~15, 다3 16~20 (실제 존재하는 페이지수 - 해당 페이지로 요청 전까지는 파악 불가)
        //6. 17P 조회시 
        //7. 1P ~ 17P까지 조회 실행 티어 - 디비전 순으로 조회 ex -> 다1 1P 100 -> 다1 10P 1000 -> 이후 11P 
        //요청시 null 값이 뜨므로 1P로 돌아가서 다2 1P 조회 반복값은 11이며 요청 Page는 1로 초기화
        //8. 해당 정보를 캐시에 저장 이후 저장된 데이터에 대해 같은 요청값이 들어오면 캐시 데이터 리턴 
        //9. 1달 주기로 캐시 초기화

        return null;
    }
}

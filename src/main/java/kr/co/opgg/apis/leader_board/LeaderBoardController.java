package kr.co.opgg.apis.leader_board;

import kr.co.opgg.apis.common.ResponseService;
import kr.co.opgg.apis.common.dto.ListResult;
import kr.co.opgg.apis.leader_board.dto.LeaderBoardRequest;
import kr.co.opgg.apis.leader_board.dto.LeaderBoardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;

import static kr.co.opgg.apis.leader_board.dto.LeaderBoardRequest.LeaderBoardTierAndDivisionDto;

@RestController
@RequestMapping("/leaderBoard")
public class LeaderBoardController {

    @Autowired
    private LeaderBoardService leaderBoardService;

    @Autowired
    private ResponseService responseService;

    @Value("${lol.leaderboard}")
    private String leaderBoardURL;

    @Value("${lol.api_key}")
    private String apiKey;

    private LeaderBoardRequest.LeaderBoardApiRequestDto leaderBoardApiRequestDto = null;

    private List<LeaderBoardResponse.LeaderBoardPageDto> leaderBoardPageList = new ArrayList<LeaderBoardResponse.LeaderBoardPageDto>();

    //캐시 제거
    //전역변수로 요청한 페이지까지 페이징해서 데이터 저장
    //page 체크 로직은 아래꺼 사용하면 될듯
    //요청시 해당 페이지가 객체에 존재하면 api 호출하지 말고 리턴
    //없으면 api 요청
    @GetMapping("")
    public ResponseEntity<ListResult<LeaderBoardResponse.LeaderBoardItemDto>> selectLeaderBoardList(LeaderBoardRequest.SearchLeaderBoardDto searchDto){
        Integer startPage = searchDto.getPage();
        Integer endPage = searchDto.getPage() + 1;

        if(leaderBoardPageList.size() >= startPage){
            List<LeaderBoardResponse.LeaderBoardItemDto> savedLeaderBoardList = leaderBoardPageList.get(startPage - 1).getLeaderBoardList();

            return ResponseEntity.ok(responseService.getListResult(savedLeaderBoardList));
        }

        if(leaderBoardApiRequestDto != null){
            Integer lastPage = leaderBoardApiRequestDto.getPage();

            if(startPage > lastPage){
                startPage = lastPage;
            }
        }

        List<LeaderBoardResponse.LeaderBoardItemDto> leaderBoardItemDtoList = new ArrayList<LeaderBoardResponse.LeaderBoardItemDto>();

        for(int i=startPage; i<endPage; i++){
            leaderBoardItemDtoList = requestLeaderBoardApi(i);
        }

        //리턴시 저장소에서 해당 page를 꺼내서 줌

        return ResponseEntity.ok(responseService.getListResult(leaderBoardItemDtoList));
    }

//    @Cacheable("leaderboard")
//return 값 100개씩 나눠서 저장
    public List<LeaderBoardResponse.LeaderBoardItemDto> requestLeaderBoardApi(Integer page){
        WebClient webClient = leaderBoardService.leaderBoardWebClient(leaderBoardURL);

        String tier = LeaderBoardTierAndDivisionDto.getTierList().get(0);
        String division = LeaderBoardTierAndDivisionDto.getDivisionList().get(0);
        Integer requestPage = 1;

        if(leaderBoardApiRequestDto != null){
            tier = leaderBoardApiRequestDto.getTier();
            division = leaderBoardApiRequestDto.getDivision();
            requestPage = leaderBoardApiRequestDto.getRequestPage() + 1;
        }else{
            leaderBoardApiRequestDto = new LeaderBoardRequest.LeaderBoardApiRequestDto();
        }

        System.out.println(leaderBoardApiRequestDto);

        List<LeaderBoardResponse.LeaderBoardItemDto> leaderBoardItemDtoList = (List<LeaderBoardResponse.LeaderBoardItemDto>) webClient.get()//client <- 위에서 만든 객체
                .uri("/" + tier + "/" + division + "?page=" + requestPage + "&api_key=" + apiKey)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<LeaderBoardResponse.LeaderBoardItemDto>>() {})
                .block();
        
        //해당 부분에서 저장 후 
        //원하는 페이지가 안나올시 아래 if문 실행

        System.out.println(leaderBoardItemDtoList);

        if(leaderBoardItemDtoList == null){
            tier = getTier(tier, division);
            division = getDivision(division);
            requestPage = 1;

            leaderBoardApiRequestDto.setTier(tier);
            leaderBoardApiRequestDto.setDivision(division);
            leaderBoardApiRequestDto.setRequestPage(requestPage);

            leaderBoardItemDtoList = requestLeaderBoardApi(page);
        }else{
            leaderBoardApiRequestDto.setPage(page);
            leaderBoardApiRequestDto.setRequestPage(requestPage);
            leaderBoardApiRequestDto.setTier(tier);
            leaderBoardApiRequestDto.setDivision(division);
        }

        return leaderBoardItemDtoList;
    }

    private String getTier(String tier, String division){
        Integer tierIndex = LeaderBoardTierAndDivisionDto.getTierList().indexOf(tier);
        Integer tierSize = LeaderBoardTierAndDivisionDto.getTierList().size();
        Integer divisionIndex = LeaderBoardTierAndDivisionDto.getDivisionList().indexOf(division);
        Integer divisionSize = LeaderBoardTierAndDivisionDto.getDivisionList().size();

        if(divisionIndex == divisionSize - 1){
            if(tierIndex > tierSize - 1){
                return LeaderBoardTierAndDivisionDto.getTierList().get(tierIndex + 1);
            }
        }

        return null;
    }

    private String getDivision(String division){
        Integer index = LeaderBoardTierAndDivisionDto.getDivisionList().indexOf(division);
        Integer size = LeaderBoardTierAndDivisionDto.getTierList().size();

        if(index > size - 1){
            return LeaderBoardTierAndDivisionDto.getDivisionList().get(index);
        }

        return null;
    }

    //return 값 100개씩 나눠서 저장하는 메서드
    private void setLeaderBoardPage(List<LeaderBoardResponse.LeaderBoardItemDto> leaderBoardItemList){
        Integer lastLeaderBoardDtoPage = 0;
        Integer leaderBoardItemCount = leaderBoardItemList.size();

        if(!leaderBoardPageList.isEmpty()){
            Integer lastIndex = leaderBoardPageList.size() - 1;
            lastLeaderBoardDtoPage = leaderBoardPageList.size();
            List<LeaderBoardResponse.LeaderBoardItemDto> lastLeaderBoardItemList = leaderBoardPageList.get(lastIndex).getLeaderBoardList();

            if(isCompletePage(lastLeaderBoardItemList)){
                List<LeaderBoardResponse.LeaderBoardItemDto> mergeLeaderBoardItemList = getMergeLeaderboardItemList(lastLeaderBoardItemList, leaderBoardItemList);
                lastLeaderBoardItemList.addAll(mergeLeaderBoardItemList);

                leaderBoardItemCount = leaderBoardItemCount - mergeLeaderBoardItemList.size();
            }
        }

        Integer remainCount = 100 % leaderBoardItemCount;

        if(isMergeable(remainCount)){

        }
    }

    private Boolean isCompletePage(List<LeaderBoardResponse.LeaderBoardItemDto> lastLeaderBoardPageDto){
        Integer lastLeaderBoardItemCount = lastLeaderBoardPageDto.size();

        if(lastLeaderBoardItemCount > 100){
            return false;
        }

        return true;
    }

    private Boolean isMergeable(Integer remainCount){
        if(remainCount <= 0){
            return true;
        }

        return false;
    }

    private List<LeaderBoardResponse.LeaderBoardItemDto> getMergeLeaderboardItemList(List<LeaderBoardResponse.LeaderBoardItemDto> lastLeaderBoardPageDto, List<LeaderBoardResponse.LeaderBoardItemDto> leaderBoardItemList){
        Integer mergeSize = 100 - lastLeaderBoardPageDto.size();
        Integer leaderBoardSize = leaderBoardItemList.size();

        if(mergeSize > leaderBoardSize){
            return leaderBoardItemList;
        }

        return leaderBoardItemList.subList(0, mergeSize);
    }
}

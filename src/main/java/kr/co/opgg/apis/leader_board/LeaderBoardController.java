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

    @GetMapping("")
    public ResponseEntity<ListResult<LeaderBoardResponse.LeaderBoardItemDto>> selectLeaderBoardList(LeaderBoardRequest.SearchLeaderBoardDto searchDto){
        WebClient webClient = leaderBoardService.leaderBoardWebClient(leaderBoardURL);
        Integer startPage = searchDto.getPage() - 1;
        Integer endPage = searchDto.getPage();

        if(leaderBoardApiRequestDto != null){
            Integer lastPage = leaderBoardApiRequestDto.getPage();

            if(startPage > lastPage){
                startPage = lastPage;
            }
        }

        List<LeaderBoardResponse.LeaderBoardItemDto> leaderBoardItemDtoList = new ArrayList<LeaderBoardResponse.LeaderBoardItemDto>();

        for(int i=startPage; i<endPage; i++){
            leaderBoardItemDtoList = requestLeaderBoardApi(i, webClient);
        }

        return ResponseEntity.ok(responseService.getListResult(leaderBoardItemDtoList));
    }

    @Cacheable("leaderboard")
    public List<LeaderBoardResponse.LeaderBoardItemDto> requestLeaderBoardApi(Integer page, WebClient webClient){
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

        List<LeaderBoardResponse.LeaderBoardItemDto> leaderBoardItemDtoList = (List<LeaderBoardResponse.LeaderBoardItemDto>) webClient.get()//client <- 위에서 만든 객체
                .uri("/" + tier + "/" + division + "?page=" + requestPage + "&api_key=" + apiKey)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<LeaderBoardResponse.LeaderBoardItemDto>>() {})
                .block();

        System.out.println(leaderBoardItemDtoList);

        if(leaderBoardItemDtoList == null){
            tier = getTier(tier, division);
            division = getDivision(division);
            requestPage = 1;

            leaderBoardApiRequestDto.setTier(tier);
            leaderBoardApiRequestDto.setDivision(division);
            leaderBoardApiRequestDto.setRequestPage(requestPage);

            leaderBoardItemDtoList = requestLeaderBoardApi(page, webClient);
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
}

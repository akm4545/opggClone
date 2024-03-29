package kr.co.opgg.apis.summoner;

import kr.co.opgg.apis.common.ResponseService;
import kr.co.opgg.apis.common.dto.ListResult;
import kr.co.opgg.apis.common.dto.SingleResult;
import kr.co.opgg.apis.summoner.dto.SummonerRequest;
import kr.co.opgg.common.exception.SearchException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.reducing;
import static kr.co.opgg.apis.summoner.dto.SummonerResponse.*;


@RestController
@RequestMapping("/summoner")
@RequiredArgsConstructor
public class SummonerController {

    @Value("${lol.summoner}")
    private String summonerURL;

    @Value("${lol.api_key}")
    private String apiKey;

    @Value("${lol.matches}")
    private String matches;

    @Value("${lol.match}")
    private String match;

    private final SummonerService summonerService;

    private final ResponseService responseService;

    @Cacheable(value = "matchParticipants", key = "#summonerRequest.summoner")
    @GetMapping("/{summoner}")
    public ResponseEntity<SingleResult<MatchResult>> selectSummoner (SummonerRequest summonerRequest) {
        String summonerName = summonerRequest.getSummoner();
        summonerRequest = setReqParam(summonerURL, summonerRequest.getSummoner() + "?" + apiKey, "SummonerInfo");
        SummonerInfo summonerResponse = (SummonerInfo) summonerService.selectSummonerMatch(summonerRequest);
        List<Match> matchParticipants = new ArrayList<>();

        Optional.ofNullable(summonerResponse).orElseThrow(SearchException.NotFoundSummonerException::new);
        summonerRequest =
                setReqParam(matches,
                        summonerResponse.getPuuid() + "/ids" + "?start="+ summonerRequest.getStartPage() + "&count="+ summonerRequest.getCount() + "&" + apiKey, "MatchIdList");
        String[] matches = (String[]) summonerService.selectSummonerMatch(summonerRequest);

        Optional.ofNullable(matches).orElseThrow(SearchException.SearchInfoNotFoundException::new);
        matchParticipants = Arrays.asList(matches).stream()
                .map(matchData -> setReqParam(match, matchData+ "?" +apiKey, "Match"))
                .map(summonerService::selectSummonerMatch)
                .map(matchParticipant -> (Match) matchParticipant)
                .collect(Collectors.toList());

        List<Map<String, MatchParticipant>> summonerMatches = matchParticipants.stream()
                .map(
                        match ->
                                match.getInfo().getParticipants().stream()
                                        .filter(matchUser -> matchUser.getSummonerName().equals(summonerName))
                                        .collect(Collectors.toMap(e -> "summoner", e -> e))
                ).collect(Collectors.toList());

//        List<Integer> totalKills = matchParticipants.stream()
////                .map(match -> match.getInfo().getParticipants().stream().map(MatchParticipant::getKills).reduce(0, (i, j) -> i + j))
//                .map(match -> match.getInfo().getParticipants().stream().map(MatchParticipant::getKills).reduce(0, (i, j) -> i + j))
//                .collect(Collectors.toList());


        MatchResult matchResult = MatchResult.builder().matches(matchParticipants).summonerMatches(summonerMatches).build();

        return ResponseEntity.ok(responseService.getSingleResult(matchResult));
    }

    public SummonerRequest setReqParam(String baseURL, String reqURL, String reqType){
        SummonerRequest summonerRequest = new SummonerRequest();
        summonerRequest.setBaseURL(baseURL);
        summonerRequest.setRequestURL(reqURL);
        summonerRequest.setReqType(reqType);

        return summonerRequest;
    }
}

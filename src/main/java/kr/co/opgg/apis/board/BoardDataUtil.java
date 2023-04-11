package kr.co.opgg.apis.board;

import kr.co.opgg.apis.board.dto.BoardResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BoardDataUtil {

    //재귀함수 처리
    public List<BoardResponse.CommentDetail> setNestedComment(List<BoardResponse.CommentDetail> commentDetailList){
        //포문 돌면서 자식 노드들을 찾아 입력
        //자식 노드들과 나머지 노드들을 합쳐 하나의 리스트로 만들기
        //하나의 리스트로 만든 리스트를 재귀로 전달
        //종료 조건은 나머지 리스트가 존재하지 않을때 재귀 종료 시점
        //이후 같은 함수를 호출하

        //return commentDetailList.stream().map().collect(Collectors.toList());
        return null;
    }
}

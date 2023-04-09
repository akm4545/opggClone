package kr.co.opgg.common.enums;

public class CodeType {

    /*
    *  FAQ
    *  [패치노트, 랭킹, 전적, 관전하기/리플레이, 챔피언 분석, 기타]
    * */
    public enum FAQType{
        PATCH, RANKING, RECORD, REPLAY, CHAMPION, ETC
    }

    /*
    * 게시글
    * [공지, 기획, 유저 뉴스, 팁, 자유, 유머, 영상, 유저찾기, 사건 사고, 팬아트]
    * */
    public enum BoardType{
        NOTICE, PLAN, NEWS, TIP, FREE, VIDEO, FIND, ISSUE, ART
    }
}

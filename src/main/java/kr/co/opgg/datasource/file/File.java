package kr.co.opgg.datasource.file;


import kr.co.opgg.common.enums.CodeType;
import kr.co.opgg.datasource.ad.Ad;
import kr.co.opgg.datasource.board.Board;
import kr.co.opgg.datasource.qna.QNA;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "FILE")
public class File {
    @Id
    @GeneratedValue
    @Column(name = "FILE_IDX")
    private Long fileIdx;

    @Column(name = "FILE_NAME")
    private String fileName;

    @Column(name = "FILE_DIRECTORY")
    private String fileDirectory;

    @Column(name = "FILE_TYPE")
    @Enumerated(EnumType.ORDINAL)
    private CodeType.NoticeType fileType; // 파일 타입 이넘으로??

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BOARD_IDX")
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "AD_IDX")
    private Ad ad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "QNA_IDX")
    private QNA qna;

    @Builder
    public File (Long fileIdx, String fileName, String fileDirectory, CodeType.NoticeType fileType, Board board, Ad ad, QNA qna){
        this.fileIdx = fileIdx;
        this.fileName = fileName;
        this.fileDirectory = fileDirectory;
        this.fileType = fileType;
        
        //양방향 관계필요
        this.board = board;
        this.ad = ad;
        this.qna = qna;
    }
}

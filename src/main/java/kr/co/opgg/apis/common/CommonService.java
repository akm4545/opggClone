package kr.co.opgg.apis.common;

import kr.co.opgg.apis.comment.dto.CommentRequest;
import kr.co.opgg.apis.common.dto.CommonRequest;
import kr.co.opgg.apis.common.dto.CommonResult;
import kr.co.opgg.datasource.board.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CommonService {
    private final ResponseService responseService;

    public CommonResult healthCheck() {
        return responseService.getSuccessResult();
    }

    @Transactional
    public Boolean fileUpload(List<MultipartFile> multipartFileList, String directory, Object domain, String type){
        File uploadDirectory = new File(directory);

        if(!uploadDirectory.exists()){
            uploadDirectory.mkdirs();
        }

        List<CommonRequest.File> fileList = multipartFileList.stream().map(file -> {
            String fileName = changeFileName(file.getOriginalFilename());
            File uploadFile = new File(directory + "\\" + fileName);

            try {
                file.transferTo(uploadFile);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            return CommonRequest.File
                    .builder()
                    .fileName(fileName)
                    .build();
        }).collect(Collectors.toList());


        if(type.equals("BOARD")){
            Board board = (Board) domain;

            List<kr.co.opgg.datasource.file.File> uploadFileData = fileList
                    .stream()
                    .map(uploadFile ->
                            kr.co.opgg.datasource.file.File
                                    .builder()
                                    .board(board)
                                    .fileName(uploadFile.getFileName())
                                    .fileDirectory(directory)
                                    .build())
                    .collect(Collectors.toList());
        }

        return true;
    }

    public String changeFileName(String fileName){
        String ext = fileName.substring(fileName.lastIndexOf("."));
        String ChangeFileName = UUID.randomUUID().toString() + ext;

        return ChangeFileName;
    }
}

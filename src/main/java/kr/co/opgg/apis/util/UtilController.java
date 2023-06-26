package kr.co.opgg.apis.util;

import kr.co.opgg.apis.common.ResponseService;
import kr.co.opgg.apis.common.dto.SingleResult;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class UtilController {

    private final ResponseService responseService;

    @GetMapping("/convert")
    public ResponseEntity<SingleResult<String>> convertStyleAttribute(){
        String html = "<div id=\"captionsOnBtn\"\n" +
                "                                                                style=\"width: 20px; height: 20px; bottom: 5px; right: 34px; position: absolute; cursor: pointer; userSelect: none; -webkit-tap-highlight-color: rgba(0, 0, 0, 0); pointerEvents: auto; display: none;\">\n" +
                "                                                                <div id=\"captionsOnIco\"\n" +
                "                                                                    style=\"width: 20px; height: 20px; top: 50%; left: 50%; transform: translate(-50%, -50%); position: relative; cursor: pointer; userSelect: none; -webkit-tap-highlight-color: rgba(0, 0, 0, 0); pointerEvents: auto;\">";
        Integer startIndex = 0;
        Integer endIndex = 0;
        String convertHtmlString = "";

        while(true){
            endIndex = html.indexOf("style=\"", startIndex);

            if(endIndex == -1){
                convertHtmlString += html.substring(startIndex);
                break;
            }

            endIndex += 6;

            convertHtmlString += html.substring(startIndex, endIndex);

            startIndex = endIndex;
            endIndex = html.indexOf(">", startIndex);

            String convertTargetHtml = html.substring(startIndex, endIndex);
            convertTargetHtml = "{{" + convertTargetHtml + "}}";
            convertTargetHtml = convertTargetHtml.replaceAll("\"", "");
            convertTargetHtml = convertTargetHtml.replaceAll(": ", ":\"");
            convertTargetHtml = convertTargetHtml.replaceAll(";", "\",");
            //convertTargetHtml = convertTargetHtml.replaceAll(" ", "");

            convertHtmlString += convertTargetHtml;

            startIndex = endIndex;
        }

        convertHtmlString = closeTag(closeTag(convertHtmlString, "img"), "input");

        System.out.println(convertHtmlString);

        return ResponseEntity.ok(responseService.getSingleResult(convertHtmlString));
    };

    public String closeTag(String html, String tag) {
        int startIndex = 0;
        int endIndex = 0;
        String convertingHTML = "";
        while(true){
            String closeTag = "";
            startIndex = html.indexOf(tag, endIndex);
            if(startIndex == -1){
                convertingHTML = html;
                break;
            }

            endIndex = html.indexOf(">", startIndex);
            convertingHTML += html.substring(0, endIndex);
            closeTag = html.substring(endIndex-1, endIndex);
            if(!closeTag.equals("/")){
                convertingHTML += "/" + html.substring(endIndex, html.length());
                html = convertingHTML;
                convertingHTML = "";
            }
            startIndex = endIndex;

        }

        return convertingHTML;
    }
}

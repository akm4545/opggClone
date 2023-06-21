package kr.co.opgg.apis.util;

import kr.co.opgg.apis.common.ResponseService;
import kr.co.opgg.apis.common.dto.SingleResult;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class UtilController {

    private final ResponseService responseService;

    @GetMapping("/convert")
    public ResponseEntity<SingleResult<String>> convertStyleAttribute(){
        String html = "<iframe marginwidth=\"0\" marginheight=\"0\"\n" +
                "                                frameborder=\"0\" scrolling=\"no\" title=\"Primis Frame\" id=\"google_ads_iframe_dummy_sekindoParent62\"\n" +
                "                                style=\"width: 1px; height: 1px; position: absolute;\">\n" +
                "                            </iframe>\n" +
                "                            <div id=\"primis_container_div\">\n" +
                "                                <div id=\"placeHolder\"\n" +
                "                                    style=\"position: relative; overflow: hidden; width: 405px; height: 228px;\">\n" +
                "                                    <div\n" +
                "                                        style=\"position: absolute; top: 50%; left: 50%; transform: translate(-50%, -50%) scale(1.62857); display: block;\">\n" +
                "                                        <div\n" +
                "                                            style=\"width: 100px;height: 100px;position: absolute;top: 50%;left: 50%;transform: translate(-50%, -50%);border: 5px solid #eeeeee;border-radius: 500px;\">\n" +
                "                                        </div>\n" +
                "                                        <div\n" +
                "                                            style=\"position: absolute;top: 50%;left: 4px;transform: translate(-50%, -50%);width: 0;height: 0;border-top: 15px solid transparent;border-left: 27px solid #eeeeee;border-bottom: 15px solid transparent;\">\n" +
                "                                        </div>\n" +
                "                                    </div>\n" +
                "                                </div>\n" +
                "                                <div id=\"primis_playerSekindoSPlayer649040c5d7a6d\"\n" +
                "                                    style=\"overflow: visible; position: fixed; width: 405px; height: 228px; box-shadow: rgba(0, 0, 0, 0.4) 1px 1px 6px 3px; border-radius: 1px; display: block; z-index: 2147483646; left: 10px; bottom: 10px;\">\n" +
                "                                    <div id=\"Player-Div-SekindoSPlayer649040c5d7a6d\"\n" +
                "                                        style=\"width: max-content; height: max-content; top: auto; left: auto; position: static; transform: none;\">\n" +
                "                                        <div id=\"closeContainer\"\n" +
                "                                            style=\"display: block; height: 21px; width: 100%; position: absolute; pointer-events: none; z-index: 5; top: -21px;\">\n" +
                "                                            <div id=\"closeIcon\"\n" +
                "                                                style=\"height: 16px; width: 16px; position: relative; float: left;\">\n" +
                "                                                <div\n" +
                "                                                    style=\"pointer-events: auto; width: 60px;height: 60px;position: absolute;top: 50%;left: 50%;transform: translate(-50%, -50%);z-index: -1;\">\n" +
                "                                                </div>";

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

        System.out.println(convertHtmlString);

        return ResponseEntity.ok(responseService.getSingleResult(convertHtmlString));
    };
}

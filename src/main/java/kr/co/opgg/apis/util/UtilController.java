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
        String html = "<div><div class=\"css-1v663t e1x14w4w1\"><div class=\"header\">솔로랭크<span class=\"unranked\">Unranked</span></div></div><div class=\"css-1474l3c e1x14w4w1\"><div class=\"header\">자유랭크<span class=\"unranked\">Unranked</span></div></div><div class=\"css-1pirsze e17e77tq8\"><div><div class=\"vm-placement\" data-id=\"625ec10e6848aa421d90623e\" data-publisher-ref=\"side-bar-ad\" data-pos=\"1100\" id=\"625ec10e6848aa421d90623e-1100\" data-reg=\"true\" style=\"background-image: url(&quot;https://hb.vntsm.com/assets/op_loader.gif&quot;); background-position: center center; background-repeat: no-repeat; position: relative; margin: 0px auto; overflow: hidden; max-width: 300px; width: 300px; height: 250px;\" data-google-query-id=\"CN6guZe71P8CFQmI6QUdVuML4w\"><div id=\"google_ads_iframe_/21726375739,114726905/VM_5a8460b346e0fb0001fc3bac/VM_625ec10e6848aa421d90623e_0__container__\" style=\"border: 0pt none; display: block; width: 300px; height: 250px; margin: 0px auto;\"><iframe frameborder=\"0\" src=\"https://f267e73910abdd76ded59f8e57c3618a.safeframe.googlesyndication.com/safeframe/1-0-40/html/container.html\" id=\"google_ads_iframe_/21726375739,114726905/VM_5a8460b346e0fb0001fc3bac/VM_625ec10e6848aa421d90623e_0\" title=\"3rd party ad content\" name=\"\" scrolling=\"no\" marginwidth=\"0\" marginheight=\"0\" width=\"300\" height=\"250\" data-is-safeframe=\"true\" sandbox=\"allow-forms allow-popups allow-popups-to-escape-sandbox allow-same-origin allow-scripts allow-top-navigation-by-user-activation\" allow=\"attribution-reporting\" role=\"region\" aria-label=\"Advertisement\" tabindex=\"0\" style=\"border: 0px; vertical-align: bottom;\" data-google-container-id=\"3\" data-load-complete=\"true\"></iframe></div></div></div></div><div class=\"css-ut2tyh e1rsywk30\"><div>같은 팀으로 게임한 소환사들 (최근 20 게임)</div><table><caption>같은 팀으로 게임한 소환사들 (최근 20 게임)</caption><colgroup><col><col width=\"60\"><col width=\"60\"><col width=\"60\"></colgroup><thead><tr><th class=\"name\" scope=\"col\">소환사</th><th class=\"played\" scope=\"col\">게임</th><th class=\"win-lose\" scope=\"col\">승 - 패</th><th class=\"winratio\" scope=\"col\">승률</th></tr></thead><tbody><tr><td class=\"name\"><a href=\"/summoners/kr/%EC%97%AD%EB%B0%9C%EC%82%B0%EA%B8%B0%EC%8A%AD\"><img src=\"https://opgg-static.akamaized.net/meta/images/profile_icons/profileIcon5091.jpg?image=q_auto,f_webp,w_48&amp;v=1687335464056\" width=\"24\" alt=\"\">역발산기슭</a></td><td class=\"played\">18</td><td class=\"win-lose\">10 - 8</td><td class=\"winratio\">56%</td></tr><tr><td class=\"name\"><a href=\"/summoners/kr/%EB%82%98%EC%9B%90%EB%9E%98%EB%AA%BB%ED%95%9C%EB%8B%A4\"><img src=\"https://opgg-static.akamaized.net/meta/images/profile_icons/profileIcon3367.jpg?image=q_auto,f_webp,w_48&amp;v=1687335464056\" width=\"24\" alt=\"\">나원래못한다</a></td><td class=\"played\">4</td><td class=\"win-lose\">1 - 3</td><td class=\"winratio\">25%</td></tr><tr><td class=\"name\"><a href=\"/summoners/kr/%EC%A7%80%ED%81%AC%EB%A6%B0%EB%8D%B0\"><img src=\"https://opgg-static.akamaized.net/meta/images/profile_icons/profileIcon3546.jpg?image=q_auto,f_webp,w_48&amp;v=1687335464056\" width=\"24\" alt=\"\">지크린데</a></td><td class=\"played\">2</td><td class=\"win-lose\">1 - 1</td><td class=\"winratio\">50%</td></tr><tr><td class=\"name\"><a href=\"/summoners/kr/%EC%A1%B4%EC%9E%AC%EA%B0%90%20%ED%99%95%EA%B3%A0%ED%95%98%EA%B2%8C\"><img src=\"https://opgg-static.akamaized.net/meta/images/profile_icons/profileIcon5799.jpg?image=q_auto,f_webp,w_48&amp;v=1687335464056\" width=\"24\" alt=\"\">존재감 확고하게</a></td><td class=\"played\">2</td><td class=\"win-lose\">1 - 1</td><td class=\"winratio\">50%</td></tr></tbody></table></div></div>";

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

package kr.co.opgg.utils.date;

import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class DateUtil {

    public String minutesAgo(LocalDateTime createDate){
        LocalDateTime now = LocalDateTime.now();

        Duration duration = Duration.between(now, createDate);

        Long diffHours = duration.toHours();
        String diffMinutes = String.valueOf(duration.toMinutes());

        if(diffHours >= 1){
            return localDateTimeToString(createDate);
        }else{
            return diffMinutes + "분 전";
        }
    }

    public String localDateTimeToString(LocalDateTime dateTime){
        return dateTime.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
    }
}

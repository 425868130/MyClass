package Utils;

import java.sql.Time;
import java.util.Calendar;

/**
 * Created by Dream Sky on 2017/3/10.
 * 获取在线时长
 */
public class TransTime {
    public static String getOnlineTime(String loginTime) {
        int hour = Integer.parseInt(loginTime.substring(11, 13));
        int mins = Integer.parseInt(loginTime.substring(14, 16));
        Calendar now = Calendar.getInstance();
        int NowHour = now.get(Calendar.HOUR_OF_DAY);
        int NowMins = now.get(Calendar.MINUTE);
        String ReTime = (NowHour - hour) + "小时" + (NowMins - mins) + "分";
        //System.out.println(ReTime);
        return ReTime;
    }
}

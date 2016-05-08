package pro.date;

import org.junit.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Near on 2015/11/25.
 */
public class TestDate {
    @Test
    public void test() {
        Date d = new Date();

        //获得当前的时间戳
        long time = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat();

        sdf.applyPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println(sdf.format(d));

        //*******************************************************************
        Date date = null;

        String pattern = "yyyy年 MM月 dd日";
        DateFormat df = new SimpleDateFormat(pattern);
        String stime = "2015年 11月 25日"; //"yyyy年 MM月 dd号"

        try {
            date = df.parse(stime);
            System.out.println(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //*******************************************************************
        Calendar calendar = new GregorianCalendar();
        calendar.set(2014, 7, 13);

        System.out.println(calendar.get(Calendar.YEAR) + "-"
                + calendar.get(Calendar.MONTH) + "-"
                + calendar.get(Calendar.DATE));
        System.out.println(calendar);
    }
}

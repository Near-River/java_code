package pro.date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

/**
 * 模拟简单的日历功能
 * Created by Near on 2015/11/26.
 */
public class VisualCalendar {
    public static void main(String[] args) {
        System.out.println("请输入日期(格式：2013-3-10)：");
        Scanner scanner = new Scanner(System.in);
        String temp = scanner.nextLine();

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = format.parse(temp);
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(date);
            // int day = calendar.get(Calendar.DATE);
            calendar.set(Calendar.DATE, 1);

            int maxDate = calendar.getActualMaximum(Calendar.DATE);
            System.out.println("日\t一\t二\t三\t四\t五\t六");

            for (int i = 1; i < calendar.get(Calendar.DAY_OF_WEEK); i++) {
                System.out.print('\t');
            }

            for (int i = 1; i <= maxDate; i++) {
                System.out.print(i + "\t");
                int w = calendar.get(Calendar.DAY_OF_WEEK);
                if (w == Calendar.SATURDAY) {
                    System.out.print('\n');
                }
                calendar.add(Calendar.DATE, 1);
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}

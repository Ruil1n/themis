package dangod.themis.util;

import java.util.Calendar;

public class TermUtil {
    public static String get(){
        int year = Calendar.getInstance().get(Calendar.YEAR);
        int month = Calendar.getInstance().get(Calendar.MONTH);
        if(month>=2&&month<8){
            return (year-1)+"02";
        }else{
            return (year)+"01";
        }

    }
}

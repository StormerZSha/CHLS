package utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

//日期与字符串转换的工具类。
public class StringDateTransformUtils {

    //
    public Date StringToDate(String s){
        try {
            DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
            Date result = dateFormat.parse(s);
            return result;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String DateToString(Date date){
        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd ");
        String result = dateFormat.format(date);
        return result;
    }


}

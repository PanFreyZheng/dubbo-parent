import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.SimpleFormatter;

/**
 * @author :LoseMyself    pengfei.zheng@hand-china.com
 * @version :1.0
 * @description :
 * @date :2018/3/26 17:10
 */
public class main {
    public static void main(String[] args) {
//        String[] a = null;
//        String  str = "/asdasd/123123/123";
//
//        List<String> array = Arrays.asList(str.split("/"));
//        a = str.split("/");
//
//        System.out.println(a.length);
//
//        System.out.println(array.size());
//        for(String s : array){
//            System.out.println("===" + s.toString());
//        }
        Long t = System.currentTimeMillis();
        System.out.println(t);
        Date date = new Date(t);
        SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        System.out.println(simpleFormat.format(date));
    }
}

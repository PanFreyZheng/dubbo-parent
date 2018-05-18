import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.sayHello;

/**
 * @author :LoseMyself    pengfei.zheng@hand-china.com
 * @version :1.0
 * @description :
 * @date :2018/5/18 9:37
 */
public class run {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("META-INF/spring/dubbo-consumer.xml");
        sayHello sayHello = (sayHello) ctx.getBean("sayHello");
        String s = sayHello.sayHello("张三");
        System.out.println(s);
    }
}

import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.KeeperException;

import java.io.IOException;

/**
 * @author :LoseMyself    pengfei.zheng@hand-china.com
 * @version :1.0
 * @description :
 * @date :2018/3/13 11:15
 */
public class run {
//    private static String path = "dubbo/test";
    public static void main(String[] args) throws KeeperException, InterruptedException {
//        CreateGroup createGroup = new CreateGroup();
//        try {
//            createGroup.connect("184.170.223.154");
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        ZkClient zkClient = new ZkClient("184.170.223.154",1000);
//        test test1 = new test(1,zkClient);

//        zkClient.createPersistent("/dubbo/abc/1",true);
//        zkClient.close();


//        createGroup.create("dubbo/test");
//        createGroup.create("dubbo/test/provider");

        test test1 = new test(1,zkClient);
        test test2 = new test(2,zkClient);

        test1.start();
        test2.start();


    }
}

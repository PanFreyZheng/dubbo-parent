import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.KeeperException;

import java.io.IOException;

/**
 * @author :LoseMyself    pengfei.zheng@hand-china.com
 * @version :1.0
 * @description :
 * @date :2018/3/22 19:51
 */
public class test extends Thread{
    private int a = 0;
    private ZkClient b = null;
    // 通过构造方法给线程名字赋值
    public test(int path,ZkClient con){
        a = path;
        b = con;
    }

    // 重写run方法，实现买票操作
    @Override
    public void run() {
        String path = "/dubbo/pre" + a;
        for(int i = 0;i < 10; ++i){
            b.createPersistent(path + i + "/providers",true);
//            try {
//                sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }
    }
}

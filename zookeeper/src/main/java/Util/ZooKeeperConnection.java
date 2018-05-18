package Util;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * @author :LoseMyself    pengfei.zheng@hand-china.com
 * @version :1.0
 * @description :
 * @date :2018/2/27 17:43
 */
public class ZooKeeperConnection {
    //        地址
    static final String address = "184.170.223.154:2181";
    //        session时间
    static final int sessionTimeout = 3000;
    //        信号量,阻塞程序执行,用于等待zk连接成功,发送成功信号
    static final CountDownLatch connectedSemaphore = new CountDownLatch(1);

    static ZooKeeper zk = null;

    public ZooKeeper connect() throws InterruptedException {
        try {
            zk = new ZooKeeper(address, sessionTimeout, new Watcher() {
                @Override
                public void process(WatchedEvent event) {
//                    获取事件的状态
                    Event.KeeperState keeperState = event.getState();
                    Event.EventType eventType = event.getType();
//                    如果是建立连接
                    if(Event.KeeperState.SyncConnected == keeperState){
                        if(Event.EventType.None == eventType){
                            // 若果建立连接成功,则发送信号量,计数器-1,让后续程序进行下去.
                            connectedSemaphore.countDown();
                        }
                    }
                    System.out.println("已经触发了" + event.getType() + "事件!");
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 等待计数器为0是主线程继续往下走
        connectedSemaphore.await();

        System.out.println("创建连接成功");

        return zk;
    }

    public void close(){
        if(this.zk != null){
            try{
                this.zk.close();
            }catch (InterruptedException e){

            }
        }
    }
}

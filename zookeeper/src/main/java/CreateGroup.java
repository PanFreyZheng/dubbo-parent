/**
 * @author :LoseMyself    pengfei.zheng@hand-china.com
 * @version :1.0
 * @description :
 * @date :2018/2/27 17:36
 */

import Util.ZooKeeperConnection;
import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import org.apache.zookeeper.Watcher;

public class CreateGroup implements Watcher {

    private static final int SESSION_TIMEOUT = 1000;// 会话延迟

    private ZooKeeper zk = null;

    private CountDownLatch countDownLatch = new CountDownLatch(1);// 同步计数器

    public void process(WatchedEvent event) {
        if(event.getState() == Event.KeeperState.SyncConnected){
            countDownLatch.countDown();// 计数器减一
        }
    }

    /**
     * 创建zk对象
     * 当客户端连接上zookeeper时会执行process(event)里的countDownLatch.countDown()，计数器的值变为0，则countDownLatch.await()方法返回。
     * @param hosts
     * @throws IOException
     */
    public void connect(String hosts) throws IOException, InterruptedException {
        zk = new ZooKeeper(hosts,SESSION_TIMEOUT,this);
        countDownLatch.await();//阻塞程序继续执行
    }

    public void create(String groupName) throws KeeperException, InterruptedException {
        String path = "/" +groupName;
        String createPath = null;
        try{
            createPath = zk.create(path,null, ZooDefs.Ids.OPEN_ACL_UNSAFE/*允许任何客户端对znode进行读写*/,CreateMode.PERSISTENT/*持久化znode*/);
        }catch (KeeperException.NodeExistsException e){
            System.out.println(e.getMessage());
        }
        System.out.println("Created" + createPath);
    }

    public void close() throws InterruptedException{
        try {
            zk.close();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            zk = null;
            System.gc();
        }
    }
}

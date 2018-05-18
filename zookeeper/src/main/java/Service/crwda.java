package Service;

import Util.ZooKeeperConnection;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;
import org.apache.zookeeper.data.Stat;
import org.apache.zookeeper.server.auth.DigestAuthenticationProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * @author :LoseMyself    pengfei.zheng@hand-china.com
 * @version :1.0
 * @description :
 * @date :2018/3/13 11:36
 */
public class crwda {

    public static void main(String[] args) throws Exception {
//            create();
//            delete();
//        exists();
//        get("/a");

//        getW("/a");
//        getW2("/a");

        try{
            acl();
        }catch (Exception e){
            System.out.println("================================" + e.getMessage());
            delete();
        }

    }

    public static void create() throws Exception {

        ZooKeeper zk = new ZooKeeperConnection().connect();

        System.out.println("start to create /TEST");
        zk.create("/TEST","This is /TEST".getBytes("utf-8"), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.println("创建/IDEA_TEST成功");
        zk.close();
    }

    public static void delete() throws Exception {
        ZooKeeper zk = new ZooKeeperConnection().connect();
        zk.delete("/authTest",-1);
        zk.close();
    }

    public static void exists() throws Exception{
        ZooKeeper zk = new ZooKeeperConnection().connect();

        Stat s1 = zk.exists("/hbase",true);
        System.out.println("=========================");
        s1 = zk.exists("/zookeeper", new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                if(event.getType() == null || "".equals(event.getType())){
                    return ;
                }
                System.out.println("已经触发了" + event.getType() + "事件!");
            }
        });
    }

    public static byte[] get(String Path) throws Exception{
        ZooKeeper zk = new ZooKeeperConnection().connect();
        return zk.getData(Path,null,new Stat());
    }

    public static void getW(String Path) throws Exception{
        ZooKeeper zk = new ZooKeeperConnection().connect();
        zk.getData(Path,true,new Stat());
        byte[] c = {1};
        zk.setData(Path,c,-1);
    }


    public static void getW2(String Path) throws Exception{
        ZooKeeper zk = new ZooKeeperConnection().connect();
//        zk.get
        zk.getData(Path, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                Event.KeeperState keeperState = event.getState();
                Event.EventType eventType = event.getType();

                if(Event.EventType.NodeDataChanged == eventType){
                    System.out.println("自定义:");
                }
            }
        }, new Stat());
        byte[] c = {2};
        zk.setData(Path,c,-1);
    }

    public static void acl() throws Exception{
        List<ACL> acls = new ArrayList<ACL>(2);
        Id id1 = new Id("digest", DigestAuthenticationProvider.generateDigest("admin:admin123"));
        ACL acl1 = new ACL(ZooDefs.Perms.ALL,id1);

        Id id2 = new Id("digest", DigestAuthenticationProvider.generateDigest("admin:admin123"));
        ACL acl2 = new ACL(ZooDefs.Perms.ALL,id2);

        acls.add(acl1);
        acls.add(acl2);

        ZooKeeper zk = new ZooKeeperConnection().connect();
        System.out.println(zk.create("/authTest","test".getBytes("utf-8"),acls,CreateMode.PERSISTENT));
        byte[] value = null;
        value = get("/authTest");
        System.out.println(value.length);
//        zk.addAuthInfo("digest", "admin:admin123".getBytes());
//        value = get("/authTest");
        System.out.println(value.length);


    }
}

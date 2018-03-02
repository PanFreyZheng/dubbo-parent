package Service;

import org.apache.zookeeper.*;

import java.io.IOException;

/**
 * @author :LoseMyself    pengfei.zheng@hand-china.com
 * @version :1.0
 * @description :
 * @date :2018/3/2 14:04
 */
public class getData
{
    public static void main(String[] args) {

        ZooKeeper zk = null;

        try{
            System.out.println("==========================");
            System.out.println("start connection zk");
            String address = "184.170.223.154:2181";
            int sessionTimeout = 3000;
            System.out.println("尝试连接zk");
            // 创建与zk的服务器连接
            zk = new ZooKeeper(address, sessionTimeout, new Watcher() {
                // 监控所有被触发的事件
                public void process(WatchedEvent event) {
                    if(event.getType() == null || "".equals(event.getType())){
                        return ;
                    }
                    System.out.println("已经触发了" + event.getType() + "事件!");
                }
            });

            System.out.println("创建连接成功!");

            Thread.currentThread().sleep(10001);

            System.out.println("==========================");
            // 创建第一个子目录节点
            System.out.println("start to create /IDEA_TEST");
            zk.create("/IDEA_TEST","我是/IDEA_TEST".getBytes("utf-8"), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            System.out.println("创建/IDEA_TEST成功");
            Thread.currentThread().sleep(10001);

            System.out.println("==========================");
            // 创建第一个子目录节点
            System.out.println("start to create /IDEA_TEST/childPath1");
            zk.create("/IDEA_TEST/childPath1","我是第一个子目录/IDEA_TEST/childPath1".getBytes("utf-8"), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            System.out.println("创建/IDEA_TEST/childPath1成功");
            Thread.currentThread().sleep(10001);

            System.out.println("======================");
            System.out.println("start to create /IDEA_TEST/childPath2");
            zk.create("/IDEA_TEST/childPath2","我是第一个子目录/IDEA_TEST/childPath2".getBytes("utf-8"), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            System.out.println("创建/IDEA_TEST/childPath2成功");
            Thread.currentThread().sleep(10001);

            System.out.println("======================");
            // 获取第二个子节点数据
            System.out.println("开始获取第二个子目录节点的节点数据...");
            System.out.println(new String(zk.getData("/IDEA_TEST/childPath1",true,null)));
            System.out.println("第一个节点获取成功");
            Thread.currentThread().sleep(10001);

            System.out.println("======================");
            // 获取第二个子节点数据
            System.out.println("开始获取第二个子目录节点的节点数据...");
            // 设置编码格式utf-8
            System.out.println(new String(zk.getData("/IDEA_TEST/childPath2",true,null),"utf-8"));
            System.out.println("第二个节点获取成功");
            Thread.currentThread().sleep(10001);

            System.out.println("===========================");
            System.out.println("开始修改/IDEA_TEST/childPath1的数据");
            zk.setData("/IDEA_TEST/childPath1","修改后的child1的数据".getBytes("utf-8"),-1);
            System.out.println("修改节点1成功");

            System.out.println("===========================");
            System.out.println("开始修改/IDEA_TEST/childPath2的数据");
            zk.setData("/IDEA_TEST/childPath2","修改后的child2的数据".getBytes(),-1);
            System.out.println("修改节点2成功");

            System.out.println("===========================");
            System.out.println("开始修改/IDEA_TEST的数据");
            zk.setData("/IDEA_TEST","修改根节点的数据".getBytes(),-1);
            System.out.println("修改根节点成功");

            System.out.println("===========================");
            System.out.println("开始删除第一个节点");
            zk.delete("/IDEA_TEST/childPath1",-1);
            System.out.println("修改节点1成功");

            System.out.println("===========================");
            System.out.println("开始删除第二个节点");
            zk.delete("/IDEA_TEST/childPath2",-1);
            System.out.println("修改节点2成功");

            System.out.println("===========================");
            System.out.println("开始删除根节点");
            zk.delete("/IDEA_TEST",-1);
            System.out.println("修改根节点成功");

            Thread.currentThread().sleep(1000l);
            System.out.println("...");
        } catch (IOException | KeeperException | InterruptedException e) {
            e.printStackTrace();
        }finally {
            if(zk != null){
                try{
                    zk.close();
                    System.out.println("close zk successful");
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }

    }
}

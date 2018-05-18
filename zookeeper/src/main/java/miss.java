import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.*;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

/**
 * @author :LoseMyself    pengfei.zheng@hand-china.com
 * @version :1.0
 * @description :
 * @date :2018/3/23 10:47
 */
public class miss {

    public static void main(String[] args) throws Exception {
//        CuratorFramework client = getClient();
//        String path = "/q";
//        final NodeCache nodeCache = new NodeCache(client,path);
//        nodeCache.start();
//        nodeCache.getListenable().addListener(new NodeCacheListener() {
//            @Override
//            public void nodeChanged() throws Exception {
//                System.out.println("监听事件触发");
//                System.out.println("重新获得节点内容为：" + new String(nodeCache.getCurrentData().getData()));
//            }
//        });
//        client.setData().forPath(path,"456".getBytes());
//        client.setData().forPath(path,"789".getBytes());
//        client.setData().forPath(path,"123".getBytes());
//        client.setData().forPath(path,"222".getBytes());
//        client.setData().forPath(path,"333".getBytes());
//        client.setData().forPath(path,"444".getBytes());
//        Thread.sleep(15000);
//    }

        CuratorFramework client = getClient();

        String path = "/q";

        final PathChildrenCache nodeCache = new PathChildrenCache(client,path,true);
        PathChildrenCacheListener pathChildrenCacheListener = new PathChildrenCacheListener() {
            @Override
            public void childEvent(CuratorFramework curatorFramework, PathChildrenCacheEvent event) throws Exception {
                System.out.println("监听事件触发");
                System.out.println("重新获得节点内容为：" + new String(String.valueOf(event.getData())));
            }
        };
        nodeCache.getListenable().addListener(pathChildrenCacheListener);
        nodeCache.start();

//        for(int i = 10; i < 20; ++i){
//            client.create().withMode(CreateMode.PERSISTENT).forPath(path + "/" + i);
//        }
//
//        Thread.sleep(150000);

    }



    private static CuratorFramework getClient(){
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000,3);
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString("184.170.223.154:2181")
                .retryPolicy(retryPolicy)
                .sessionTimeoutMs(6000)
                .connectionTimeoutMs(3000)
                .namespace("demo")
                .build();
        client.start();
        return client;
    }
}
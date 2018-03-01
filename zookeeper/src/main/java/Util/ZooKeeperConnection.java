package Util;

import org.apache.zookeeper.ZooKeeper;

import java.util.concurrent.CountDownLatch;

/**
 * @author :LoseMyself    pengfei.zheng@hand-china.com
 * @version :1.0
 * @description :
 * @date :2018/2/27 17:43
 */
public class ZooKeeperConnection {

    private ZooKeeper zoo;

    final CountDownLatch connectedSignal = new CountDownLatch(1);
}

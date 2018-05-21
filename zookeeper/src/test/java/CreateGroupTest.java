import org.apache.zookeeper.KeeperException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * @author :LoseMyself    pengfei.zheng@hand-china.com
 * @version :1.0
 * @description :
 * @date :2018/2/28 9:30
 */
public class CreateGroupTest {

    private static String hosts = "184.170.223.154";

    private static String groupName = "idea";

    private CreateGroupTest createGroup = null;

    /**
     * init
     * @throws InterruptedException
     * @throws KeeperException
     * @throws IOException
     */
    @Before
    public void init() throws KeeperException, InterruptedException, IOException {
        createGroup = new CreateGroup();
        createGroup.connect(hosts);
    }

    @Test
    public void testCreateGroup() throws KeeperException, InterruptedException {
        createGroup.create(groupName);
    }

    /**
     * 销毁资源
     */
    @After
    public void destroy() {
        try {
            createGroup.close();
            createGroup = null;
            System.gc();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

package service;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.ServiceConfig;
import service.impl.ISayHelloImpl;

/**
 * @author :LoseMyself    pengfei.zheng@hand-china.com
 * @version :1.0
 * @description :
 * @date :2018/5/18 13:25
 */
public class apiRun {

    public static void main(String[] args) {
        sayHello sayHello = new ISayHelloImpl();
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName("api-provider");

        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setAddress("zookeeper://184.170.223.154:2181");

        ProtocolConfig protocol = new ProtocolConfig();
        protocol.setName("dubbo");
        protocol.setPort(8888);
        protocol.setThreads(200);

        ServiceConfig<sayHello> service = new ServiceConfig<sayHello>(); // 此实例很重，封装了与注册中心的连接，请自行缓存，否则可能造成内存和连接泄漏
        service.setApplication(applicationConfig);
        service.setRegistry(registryConfig); // 多个注册中心可以用setRegistries()
        service.setProtocol(protocol); // 多个协议可以用setProtocols()
        service.setInterface(sayHello.class);
        service.setRef(sayHello);
//        service.setVersion("1.0.0");

        // 暴露及注册服务
        service.export();
    }

}

package service.impl;

import service.sayHello;

/**
 * @author :LoseMyself    pengfei.zheng@hand-china.com
 * @version :1.0
 * @description :
 * @date :2018/5/18 9:25
 */
public class ISayHelloImpl implements sayHello {

    public String sayHello(String name) {
        return "Hello" + name;
    }
}

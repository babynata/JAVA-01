package classnine.aop;

import classnine.aop.entity.ISchool;
import classnine.aop.entity.MySchool;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Proxy;

@Slf4j
public class JdkProxyDemo {

    public static void main(String[] args){
        MySchool mySchool = new MySchool();
        ISchool proxyObj = (ISchool) Proxy.newProxyInstance(ISchool.class.getClassLoader(), new Class[]{ISchool.class}, (proxy, method, a) -> {
            log.info("before running...");
            Object obj = method.invoke(mySchool, a);
            log.info("after running...");
            return obj;
        });
        proxyObj.ding();
    }
}

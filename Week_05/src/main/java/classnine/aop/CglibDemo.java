package classnine.aop;

import classnine.aop.entity.MySchool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;

@Slf4j
public class CglibDemo {

    public static void main(String[] args){
        MySchool mySchool = new MySchool();
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(MySchool.class);
        enhancer.setCallback((MethodInterceptor) (o, method, objects, methodProxy) -> {
            log.info("before running...");
            Object obj = method.invoke(mySchool, objects);
            log.info("after running...");
            return obj;
        });
        MySchool proxy = (MySchool) enhancer.create();
        proxy.ding();
    }
}

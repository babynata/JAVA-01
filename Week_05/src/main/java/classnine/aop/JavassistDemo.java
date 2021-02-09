package classnine.aop;

import classnine.aop.entity.MySchool;
import javassist.*;

public class JavassistDemo {

    public static void main(String[] args) throws NotFoundException, CannotCompileException, IllegalAccessException, InstantiationException {
        ClassPool classPool = ClassPool.getDefault();
        CtClass mySchoolProxy = classPool.get("classnine.aop.entity.MySchool");
        CtMethod ding = mySchoolProxy.getDeclaredMethod("ding");
        ding.insertBefore("System.out.println(\"before running...\");");
        ding.insertAfter("System.out.println(\"after running...\");");

        MySchool proxy = (MySchool) mySchoolProxy.toClass().newInstance();
        proxy.ding();
    }

}

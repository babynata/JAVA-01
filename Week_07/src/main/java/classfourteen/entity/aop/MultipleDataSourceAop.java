package classfourteen.entity.aop;

import classfourteen.manager.MultipleDataSourcesHolder;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MultipleDataSourceAop {

    @Pointcut("@annotation(classfourteen.entity.annotations.MasterDataSource)" +
              "|| execution(* classfourteen.service..*.add*(..))" +
              "|| execution(* classfourteen.service..*.update*(..))" +
              "|| execution(* classfourteen.service..*.delete*(..))")
    public void masterPointcut() {

    }

    @Pointcut("@annotation(classfourteen.entity.annotations.SlaveDataSource)" +
            "|| execution(* classfourteen.service..*.select*(..))")
    public void slavePointcut() {

    }

    @Before("masterPointcut()")
    public void switchToMaster() {
        MultipleDataSourcesHolder.master();
    }

    @Before("slavePointcut()")
    public void switchToSlave() {
        MultipleDataSourcesHolder.slave();
    }
}

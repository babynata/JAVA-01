package classfourteen.manager;

import classfourteen.entity.MultipleDataSourcesEnum;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MultipleDataSourcesHolder {

    private static final ThreadLocal<MultipleDataSourcesEnum> HOLDER = new ThreadLocal<>();

    public static void set(MultipleDataSourcesEnum dataSourcesEnum) {
        HOLDER.set(dataSourcesEnum);
    }

    public static MultipleDataSourcesEnum get() {
        return HOLDER.get();
    }

    public static void master() {
        set(MultipleDataSourcesEnum.MASTER);
        log.info("switch to master...");
    }

    public static void slave() {
        set(MultipleDataSourcesEnum.SLAVE1);
        log.info("switch to slave...");
    }
}

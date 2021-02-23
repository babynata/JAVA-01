package classten.homework_06.service;

import java.sql.SQLException;
import java.util.List;

public interface JdbcBatchService {

    int batchExecute(String sql, List<List<Object>> params) throws SQLException;
}

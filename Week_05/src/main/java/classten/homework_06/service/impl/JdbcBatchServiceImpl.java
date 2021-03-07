package classten.homework_06.service.impl;

import classten.homework_06.service.JdbcConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class JdbcBatchServiceImpl extends BaseJdbcBatchService{

    public JdbcBatchServiceImpl(JdbcConnectionFactory jdbcConnectionFactory) {
        super(jdbcConnectionFactory);
    }

    @Override
    public int batchExecute(String sql, List<List<Object>> params) throws SQLException {
        Connection connection = jdbcConnectionFactory.getConnection();
        connection.setAutoCommit(false);
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        for (List<Object> sublist : params) {
            for (int i = 0; i < sublist.size(); i++) {
                int index = i + 1;
                preparedStatement.setObject(index, sublist.get(i));
            }
            preparedStatement.addBatch();
        }

        int[] resultSets = preparedStatement.executeBatch();
        //key point
        connection.setAutoCommit(true);
        return resultSets.length;
    }
}

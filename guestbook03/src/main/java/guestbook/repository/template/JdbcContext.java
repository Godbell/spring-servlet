package guestbook.repository.template;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DataSourceUtils;

public class JdbcContext {
    private final DataSource dataSource;

    public JdbcContext(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public <E> List<E> findMany(String sql, RowMapper<E> rowMapper, Object... parameters) {
        return executeQuery(
            connection -> connection.prepareStatement(sql),
            rowMapper,
            false,
            parameters
        );
    }

    public <E> E findOne(String sql, RowMapper<E> rowMapper, Object... parameters) {
        return executeQuery(
            connection -> connection.prepareStatement(sql),
            rowMapper,
            true,
            parameters
        ).getFirst();
    }

    public int update(String sql, Object... parameters) {
        return executeUpdate(
            connection -> connection.prepareStatement(sql),
            parameters
        );
    }

    private int executeUpdate(
        StatementStrategy statementStrategy,
        Object... parameters
    ) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DataSourceUtils.getConnection(dataSource);
            preparedStatement = statementStrategy.prepareStatement(connection);

            for (int i = 0; i < parameters.length; ++i) {
                preparedStatement.setObject(i + 1, parameters[i]);
            }

            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("sql error: " + e.getMessage());
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    DataSourceUtils.releaseConnection(connection, dataSource);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        return 0;
    }

    private <E> List<E> executeQuery(
        StatementStrategy statementStrategy, RowMapper<E> rowMapper, boolean findOne, Object... parameters
    ) {
        List<E> list = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DataSourceUtils.getConnection(dataSource);
            preparedStatement = statementStrategy.prepareStatement(connection);

            for (int i = 0; i < parameters.length; ++i) {
                preparedStatement.setObject(i + 1, parameters[i]);
            }

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                E row = rowMapper.mapRow(resultSet, resultSet.getRow());
                list.add(row);

                if (findOne)
                    return list;
            }
        } catch (SQLException e) {
            System.out.println("sql error: " + e.getMessage());
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    DataSourceUtils.releaseConnection(connection, dataSource);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        return list;
    }
}

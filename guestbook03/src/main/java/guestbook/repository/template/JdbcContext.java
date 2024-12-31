package guestbook.repository.template;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.RowMapper;

public class JdbcContext {
    private final DataSource dataSource;

    public JdbcContext(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public <E> List<E> findMany(String sql, RowMapper<E> rowMapper) {
        return executeQuery(
            connection -> connection.prepareStatement(sql),
            rowMapper
        );
    }

    public int update(String sql, Object[] parameters) {
        return executeUpdate(
            connection -> connection.prepareStatement(sql),
            parameters
        );
    }

    private int executeUpdate(
        StatementStrategy statementStrategy,
        Object[] parameters
    ) {
        try (
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = statementStrategy.prepareStatement(connection)
        ) {
            for (int i = 0; i < parameters.length; ++i) {
                preparedStatement.setObject(i + 1, parameters[i]);
            }

            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("sql error: " + e.getMessage());
        }

        return 0;
    }

    private <E> List<E> executeQuery(StatementStrategy statementStrategy, RowMapper<E> rowMapper) {
        List<E> list = new ArrayList<>();

        try (
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = statementStrategy.prepareStatement(connection);
            ResultSet resultSet = preparedStatement.executeQuery();
        ) {
            while (resultSet.next()) {
                E row = rowMapper.mapRow(resultSet, resultSet.getRow());
                list.add(row);
            }
        } catch (SQLException e) {
            System.out.println("sql error: " + e.getMessage());
        }

        return list;
    }
}

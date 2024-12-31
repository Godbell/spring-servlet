package guestbook.repository.template;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;
public class JdbcContext {
    private final DataSource dataSource;

    public JdbcContext(DataSource dataSource) {
        this.dataSource = dataSource;
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
}

package guestbook.repository.template;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public interface StatementStrategy {
    public PreparedStatement prepareStatement(Connection connection) throws SQLException;
}

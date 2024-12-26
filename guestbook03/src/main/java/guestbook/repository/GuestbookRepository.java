package guestbook.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import guestbook.vo.GuestbookVo;

@Repository
public class GuestbookRepository {
    public List<GuestbookVo> findAll() {
        List<GuestbookVo> list = new ArrayList<>();

        try (
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("""
                SELECT
                    id, name, contents,
                    DATE_FORMAT(guestbook.reg_date, '%Y-%m-%d %h:%i:%s') AS reg_date_formatted,
                    ROW_NUMBER() over (ORDER BY reg_date DESC) AS guestbook_index
                FROM guestbook;
                """
            )
        ) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                GuestbookVo vo = new GuestbookVo();
                vo.setId(resultSet.getLong("id"));
                vo.setName(resultSet.getString("name"));
                vo.setRegDate(resultSet.getString("reg_date_formatted"));
                vo.setContents(resultSet.getString("contents"));
                vo.setIndex(resultSet.getInt("guestbook_index"));

                list.add(vo);
            }
        } catch (SQLException e) {
            System.out.println("sql error: " + e.getMessage());
        }

        return list;
    }

    public void add(GuestbookVo vo) {
        try (
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("""
                INSERT INTO guestbook
                    (name, password, contents, reg_date)
                VALUES (?, ?, ?, CURRENT_TIMESTAMP());
                """
            )
        ) {
            preparedStatement.setString(1, vo.getName());
            preparedStatement.setString(2, vo.getPassword());
            preparedStatement.setString(3, vo.getContents());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("sql error: " + e.getMessage());
        }
    }

    public void deleteByIdAndPassword(Long id, String password) {
        try (
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("""
                DELETE FROM guestbook WHERE id = ? AND password = ?;
                """
            );
        ) {
            preparedStatement.setLong(1, id);
            preparedStatement.setString(2, password);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("sql error: " + e.getMessage());
        }
    }

    private Connection getConnection() throws SQLException {
        Connection conn = null;

        try {
            Class.forName("org.mariadb.jdbc.Driver");

            String url = "jdbc:mariadb://192.168.64.30:3306/webdb";
            conn = DriverManager.getConnection(url, "webdb", "webdb");
        } catch (ClassNotFoundException e) {
            System.out.println("드라이버 로딩 실패:" + e);
        }

        return conn;
    }
}

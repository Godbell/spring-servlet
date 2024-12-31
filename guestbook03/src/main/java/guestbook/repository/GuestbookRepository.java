package guestbook.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

import guestbook.repository.template.JdbcContext;
import guestbook.vo.GuestbookVo;

@Repository
public class GuestbookRepository {
    private final DataSource dataSource;
    private final JdbcContext jdbcContext;

    public GuestbookRepository(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcContext = new JdbcContext(dataSource);
    }

    public List<GuestbookVo> findAll() {
        List<GuestbookVo> list = new ArrayList<>();

        try (
            Connection connection = dataSource.getConnection();
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

    public int add(GuestbookVo vo) {
        return jdbcContext.update(
            """
                INSERT INTO guestbook
                    (name, password, contents, reg_date)
                VALUES
                    (?, ?, ?, NOW());
                """,
            new Object[] {vo.getName(), vo.getPassword(), vo.getContents()}
        );
    }

    public int deleteByIdAndPassword(Long id, String password) {
        return jdbcContext.update(
            """
                DELETE FROM guestbook WHERE id = ? AND password = ?;
                """,
            new Object[] {id, password}
        );
    }
}

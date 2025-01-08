package guestbook.repository;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import guestbook.repository.template.JdbcContext;
import guestbook.vo.GuestbookVo;

@Repository
public class GuestbookRepository {
    private final JdbcContext jdbcContext;

    public GuestbookRepository(JdbcContext jdbcContext) {
        this.jdbcContext = jdbcContext;
    }

    public List<GuestbookVo> findAll() {
        return jdbcContext.findMany(
            """
                SELECT
                    id, name, contents,
                    DATE_FORMAT(guestbook.reg_date, '%Y-%m-%d %h:%i:%s') AS reg_date_formatted,
                    ROW_NUMBER() over (ORDER BY reg_date DESC) AS guestbook_index
                FROM guestbook;
                """,
            new BeanPropertyRowMapper<>(GuestbookVo.class)
        );
    }

    public int add(GuestbookVo vo) {
        return jdbcContext.update(
            """
                INSERT INTO guestbook
                    (name, password, contents, reg_date)
                VALUES
                    (?, ?, ?, NOW());
                """,
            vo.getName(), vo.getPassword(), vo.getContents()
        );
    }

    public int deleteByIdAndPassword(Long id, String password) {
        return jdbcContext.update(
            """
                DELETE FROM guestbook WHERE id = ? AND password = ?;
                """,
            id, password
        );
    }
}

package guestbook.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import guestbook.repository.GuestbookLogRepository;
import guestbook.repository.GuestbookRepository;
import guestbook.vo.GuestbookVo;

@Service
public class GuestbookService {
    private final GuestbookRepository guestbookRepository;
    private final GuestbookLogRepository guestbookLogRepository;
    private final DataSource dataSource;
    private final PlatformTransactionManager transactionManager;

    public GuestbookService(
        GuestbookRepository guestbookRepository,
        GuestbookLogRepository guestbookLogRepository,
        DataSource dataSource,
        PlatformTransactionManager transactionManager
    ) {
        this.guestbookRepository = guestbookRepository;
        this.guestbookLogRepository = guestbookLogRepository;
        this.dataSource = dataSource;
        this.transactionManager = transactionManager;
    }

    public List<GuestbookVo> getGuestbooks() {
        return guestbookRepository.findAll();
    }

    public void add(GuestbookVo vo) {
        TransactionSynchronizationManager.initSynchronization();
        Connection connection = DataSourceUtils.getConnection(dataSource);

        try {
            // tx bg
            connection.setAutoCommit(false);

            int count = guestbookLogRepository.update();

            if (count == 0) {
                guestbookLogRepository.insert();
            }

            guestbookRepository.insert(vo);

            connection.commit();
        } catch (Throwable e) {
            try {
                connection.rollback();
            } catch (SQLException ignore) {
            }

            throw new RuntimeException(e);
        } finally {
            DataSourceUtils.releaseConnection(connection, dataSource);
        }
    }

    public void delete(Long id, String password) {
        TransactionStatus transactionStatus = transactionManager.getTransaction(new DefaultTransactionDefinition());

        try {
            GuestbookVo vo = guestbookRepository.findById(id);

            if (vo == null) {
                return;
            }

            int count = guestbookRepository.deleteByIdAndPassword(id, password);

            if (count == 1) {
                guestbookLogRepository.update(vo.getRegDate());
            }

            transactionManager.commit(transactionStatus);
        } catch (Throwable e) {
            transactionManager.rollback(transactionStatus);
        }
    }
}

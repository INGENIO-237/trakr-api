package com._naptic.trakr_api.transactions;

import com._naptic.trakr_api.users.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.time.LocalDateTime;
import java.util.List;

@NoRepositoryBean
public interface TransactionRepository<T> extends JpaRepository<T, String> {
    List<T> findByUser(User user);

    Page<T> findByUser(User user, Pageable pageable);

    Page<T> findByUserAndCreatedAtBetween(User user, LocalDateTime from, LocalDateTime to, Pageable pageable);
}

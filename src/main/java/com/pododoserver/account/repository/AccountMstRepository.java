package com.pododoserver.account.repository;

import com.pododoserver.account.entity.AccountET;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountMstRepository extends JpaRepository<AccountET, Long> {
}

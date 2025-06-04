package com.pododoserver.security.jwt.repository;

import com.pododoserver.security.jwt.entity.RefreshTokenET;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshTokenET, Long> {
    boolean existsByAccountMstIdAndToken(Long accountMstId, String token);
    void deleteByAccountMstId(Long accountMstId);
    Optional<RefreshTokenET> findByToken(String token);
    @Transactional
    void deleteByToken(String refreshToken);
}

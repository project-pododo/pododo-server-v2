package com.pododoserver.security.jwt.entity;


import com.pododoserver.common.entity.BaseET;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Entity
@Table(name = "refresh_token")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class RefreshTokenET extends BaseET {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "refresh_token_id")
    private Long refreshTokenId;

    @JoinColumn(name = "account_mst_id", nullable = false)
    private Long accountMstId;

    @Column(name = "token", nullable = false, unique = true, length = 512)
    private String token;

    @Column(name = "issued_at", nullable = false)
    private LocalDateTime issuedAt;

    @Column(name = "expires_at", nullable = false)
    private LocalDateTime expiresAt;
}

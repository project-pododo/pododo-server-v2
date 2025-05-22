package com.pododoserver.account.entity;

import com.pododoserver.account.constant.Role;
import com.pododoserver.common.entity.BaseET;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
@Table(name = "account_mst")
public class AccountET extends BaseET {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_mst_id", nullable = false, updatable = false)
    private Long accountMstId;

    @Column(name = "account_login_id", nullable = false, length = 20, unique = true)
    private String accountLoginId;

    @Column(name = "account_email", nullable = false, length = 20)
    private String accountEmail;

    @Column(name = "provider", nullable = false, length = 20, unique = true)
    private String provider;

    @Column(name = "provider_id", nullable = false, length = 20)
    private String providerId;

    @Column(name = "account_name", nullable = false, length = 20)
    private String accountName;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role; // USER, ADMIN 등
}

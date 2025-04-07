package com.pododoserver.account.entity;

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

    @Column(name = "account_login_pw", nullable = false, length = 50)
    private String accountLoginPw;


    public void updatePw(String newPw) {
        this.accountLoginPw = newPw;
    }

}

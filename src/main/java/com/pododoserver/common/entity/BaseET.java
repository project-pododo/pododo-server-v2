package com.pododoserver.common.entity;

import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseET {

    @Column(name = "delete_yn", nullable = false, length = 1)
    private String deleteYn = "N";

    @CreatedDate
    @Column(name = "reg_date", nullable = false, updatable = false)
    private LocalDateTime regDate;

    @CreatedBy
    @Column(name = "reg_id", nullable = false)
    private Long regId;

    @LastModifiedDate
    @Column(name = "upd_date", nullable = false)
    private LocalDateTime updDate;

    @LastModifiedBy
    @Column(name = "mod_id", nullable = false)
    private Long modId;

}
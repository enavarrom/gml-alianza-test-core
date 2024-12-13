package com.enavarrom.tests.gml.alianza.customer.infrastructure.adapter.jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class JpaClient {

    @Id
    @Column(nullable = false)
    private String sharedKey;

    @Column(nullable = false)
    private String businessId;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String phone;

    private Date startDate;

    private Date endDate;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    @Setter(AccessLevel.NONE)
    private LocalDateTime createdDate;

}

package org.ps.paymentservice.adapters.out.repository.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.ps.paymentservice.application.core.domain.enums.PaymentStatusEnum;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString(exclude = {"paymentDetails"})
@Table(name = "payment")
public final class PaymentEntity extends AbstractMappedEntity implements Serializable {

    @Id
    @Column(name = "payment_id", updatable = false, nullable = false)
    private String id;

    private String userId;

    private String orderId;

    private BigDecimal value;

    @JsonIgnore
    @OneToOne(mappedBy = "payment", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private PaymentDetailsEntity paymentDetails;

    @Enumerated(EnumType.STRING)
    private PaymentStatusEnum paymentStatus;
}

package org.ps.paymentservice.adapters.out.repository.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.ps.paymentservice.application.core.domain.enums.PaymentStatusEnum;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "payment_details")
@ToString
public class PaymentDetailsEntity {
    @Id
    private String id;
    private String paymentMode;
    private String accountno;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "payment_id", unique = true, nullable = false)
    private PaymentEntity payment;

}

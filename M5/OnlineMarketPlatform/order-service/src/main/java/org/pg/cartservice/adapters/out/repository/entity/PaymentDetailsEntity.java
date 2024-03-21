//package org.pg.orderservice.adapters.out.repository.entity;
//
//import javax.persistence.*;
//import lombok.*;
//
//import java.io.Serializable;
//import java.util.UUID;
//
//@Entity
//@Table(name = "payment_details")
//@NoArgsConstructor
//@AllArgsConstructor
//@EqualsAndHashCode
//@Data
//@Builder
//public class PaymentDetailsEntity implements Serializable {
//    @Id
//    private UUID id;
//
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "order_id",unique = true, nullable = false )
//    private OrderEntity order;
//
//    @Column(name = "payment_mode")
//    private String paymentMode;
//    @Column(name = "account_no")
//    private String accountno;
//}

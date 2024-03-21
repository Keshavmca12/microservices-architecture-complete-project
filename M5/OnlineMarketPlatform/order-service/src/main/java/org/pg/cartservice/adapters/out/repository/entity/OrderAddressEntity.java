package org.pg.cartservice.adapters.out.repository.entity;

import javax.persistence.*;

import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "order_address")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class OrderAddressEntity extends AbstractMappedEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id", unique = true, nullable = false)
    private OrderEntity order;

    @Column(name = "full_address")
    private String fullAddress;

    @Column(name = "postal_code")
    private String postalCode;

    private String city;
    private String state;
    private String country;
    private int version;

}

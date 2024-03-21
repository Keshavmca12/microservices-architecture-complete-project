package org.ps.shippingservice.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ps.shippingservice.constant.AppConstant;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "shipment")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public final class Shipment extends AbstractMappedEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "shipment_id", updatable = false, nullable = false)
    private UUID shipmentId;
    @Column(name = "user_id")
    private UUID userId;

    @OneToOne(mappedBy = "shipment", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private ShippingAddress address;

    @Enumerated(EnumType.STRING)
    private ShippingStatus status;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(pattern = AppConstant.LOCAL_DATE_TIME_FORMAT, shape = JsonFormat.Shape.STRING)
    @DateTimeFormat(pattern = AppConstant.LOCAL_DATE_TIME_FORMAT)
    private LocalDateTime shipmentDate;
}









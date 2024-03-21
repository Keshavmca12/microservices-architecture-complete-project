package org.pg.cartservice.adapters.out.repository.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.io.Serializable;
import java.time.Instant;

@MappedSuperclass
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@ToString
abstract public class AbstractMappedEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@CreatedDate
	@JsonIgnore
	@JsonFormat(shape = Shape.STRING)
	@Column(name = "created_at")
	private Instant createdAt;
	
	@LastModifiedDate
	@JsonIgnore
	@JsonFormat(shape = Shape.STRING)
	@Column(name = "updated_at")
	private Instant updatedAt;
	
}











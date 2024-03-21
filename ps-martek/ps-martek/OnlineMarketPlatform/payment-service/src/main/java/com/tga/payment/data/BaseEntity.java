package com.tga.payment.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.index.Indexed;

import java.time.LocalDate;

@Data
public abstract class BaseEntity {

	@Id
	@Indexed
	protected String id;

	@JsonIgnore
	@CreatedDate
	protected LocalDate createdDate;
	
	protected String createdBy;
	
	protected String UpdatedBy;

	@JsonIgnore
	@LastModifiedDate
	protected LocalDate updateDate;

	@JsonIgnore
	@Version
	private Long version;
}

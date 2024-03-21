package com.tga.product.service.entity;

import java.time.LocalDate;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.index.Indexed;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public abstract class BaseEntity {

	@Id
	@Indexed
	protected String id;

	@Indexed
	@JsonIgnore
	protected boolean active = true;

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

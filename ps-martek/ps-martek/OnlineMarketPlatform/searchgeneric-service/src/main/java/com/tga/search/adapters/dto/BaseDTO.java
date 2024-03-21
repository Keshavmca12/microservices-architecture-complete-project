package com.tga.search.adapters.dto;

import java.time.LocalDate;

public abstract class BaseDTO {
	
	protected boolean active = true;

	protected LocalDate createdDate;
	
	protected String createdBy;
	
	protected String UpdatedBy;

	protected LocalDate updateDate;

}

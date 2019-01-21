package com.currency.conversion.services.dto;

import java.math.BigDecimal;

public class CurrencyConversionDTO {

	private Long id;
	private String from;
	private String to;
	private BigDecimal quantity;
	private int port;
	private BigDecimal conversionValue;
	private BigDecimal conversionMultiple;

	public CurrencyConversionDTO() {
		super();
	}

	public CurrencyConversionDTO(Long id, String from, String to, BigDecimal quantity, int port,
			BigDecimal conversionMultiple, BigDecimal conversionValue) {
		super();
		this.id = id;
		this.from = from;
		this.to = to;
		this.quantity = quantity;
		this.port = port;
		this.conversionValue = conversionValue;
		this.conversionMultiple = conversionMultiple;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public BigDecimal getConversionValue() {
		return conversionValue;
	}

	public void setConversionValue(BigDecimal conversionValue) {
		this.conversionValue = conversionValue;
	}

	public BigDecimal getConversionMultiple() {
		return conversionMultiple;
	}

	public void setConversionMultiple(BigDecimal conversionMultiple) {
		this.conversionMultiple = conversionMultiple;
	}

}

package com.example.demo.response;

public class CountBookItemResponse {
	private Integer quantity;
	
	private Integer available;
	
	public CountBookItemResponse() {
		
	}
	
	public CountBookItemResponse(Integer quantity, Integer avaiable) {
		this.available = avaiable;
		this.quantity = quantity;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getAvailable() {
		return available;
	}

	public void setAvailable(Integer available) {
		this.available = available;
	}
	
	
}

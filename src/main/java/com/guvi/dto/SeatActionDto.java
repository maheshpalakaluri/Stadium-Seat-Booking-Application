package com.guvi.dto;

import com.guvi.entity.Stand;

public class SeatActionDto {
	private Stand stand;
	private String rowLabel;
	private Integer seatNo;
	
	public Stand getStand() {
		return stand;
	}
	
	public void setStand(Stand stand) {
		this.stand = stand;
	}
	
	public String getRowLabel() {
		return rowLabel;
	}
	
	public void setRowLabel(String rowLabel) {
		this.rowLabel = rowLabel;
	}
	
	public Integer getSeatNo() {
		return seatNo;
	}
	
	public void setSeatNo(Integer seatNo) {
		this.seatNo = seatNo;
	}
	
	
	
}

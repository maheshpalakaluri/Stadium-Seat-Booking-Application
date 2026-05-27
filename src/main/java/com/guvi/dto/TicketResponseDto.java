package com.guvi.dto;

import com.guvi.entity.Stand;

public class TicketResponseDto {
	
	private Integer ticketId;
	private Stand stand;
	private String rowLabel;
	private Integer seatNo;
	private String status;
	public TicketResponseDto(Integer ticketId, Stand stand, String rowLabel, Integer seatNo, String status) {
		super();
		this.ticketId = ticketId;
		this.stand = stand;
		this.rowLabel = rowLabel;
		this.seatNo = seatNo;
		this.status = status;
	}
	public TicketResponseDto() {
	}
	public Integer getTicketId() {
		return ticketId;
	}
	public void setTicketId(Integer ticketId) {
		this.ticketId = ticketId;
	}
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	

}

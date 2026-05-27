package com.guvi.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="seats")
public class Seat {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer seatId;
	
	@Column(nullable=false)
	@Enumerated(EnumType.STRING)
	private Stand stand;
	
	@Column(nullable=false)
	private String rowLabel;
	
	@Column(nullable=false)
    private Integer seatNo;
	
	@Column(nullable=false)
	private Boolean enabled=true;
    
	public Seat() {
		
	}
	public int getSeatId() {
		return seatId;
	}

	public void setSeatId(int seatId) {
		this.seatId = seatId;
	}

	public Stand getStands() {
		return stand;
	}

	public void setStands(Stand stand) {
		this.stand = stand;
	}

	public String getRowLabel() {
		return rowLabel;
	}

	public void setRowLabel(String rowLabel) {
		this.rowLabel = rowLabel;
	}

	public int getSeatNo() {
		return seatNo;
	}

	public void setSeatNo(int seatNo) {
		this.seatNo = seatNo;
	}

	public Boolean getEnabled() {
		return enabled;
	}
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public String getSeatDesc() {
		return "Seats [seatId=" + seatId + ", Stand=" + stand + ", rowLabel=" + rowLabel + ", seatNo=" + seatNo + "]";
	}	
	
	
	

}

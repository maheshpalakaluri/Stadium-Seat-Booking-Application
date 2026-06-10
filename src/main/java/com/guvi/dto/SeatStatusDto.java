package com.guvi.dto;

import com.guvi.entity.Stand;

public class SeatStatusDto {

    private Integer seatId;
    private Stand stands;
    private String rowLabel;
    private Integer seatNo;
    private Boolean enabled;
    private Boolean booked;

    public SeatStatusDto() {
    }

    public SeatStatusDto(Integer seatId, Stand stands, String rowLabel,
                         Integer seatNo, Boolean enabled, Boolean booked) {
        this.seatId = seatId;
        this.stands = stands;
        this.rowLabel = rowLabel;
        this.seatNo = seatNo;
        this.enabled = enabled;
        this.booked = booked;
    }

    public Integer getSeatId() {
        return seatId;
    }

    public void setSeatId(Integer seatId) {
        this.seatId = seatId;
    }

    public Stand getStands() {
        return stands;
    }

    public void setStands(Stand stands) {
        this.stands = stands;
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

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Boolean getBooked() {
        return booked;
    }

    public void setBooked(Boolean booked) {
        this.booked = booked;
    }
}
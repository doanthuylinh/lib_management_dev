/////////////////////////////////////////////////////////////////////////////
//
// © 2021 IDTU-CS3332IRFA-21TSP
//
/////////////////////////////////////////////////////////////////////////////

package com.example.demo.bean;

import java.io.Serializable;
import java.time.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.example.demo.data.ReservationStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

/**
 * [OVERVIEW] Reservation Entity.
 *
 * @author: LinhDT
 * @version: 1.0
 * @History
 * [NUMBER]  [VER]     [DATE]          [USER]             [CONTENT]
 * --------------------------------------------------------------------------
 * 001       1.0       2021/04/21      LinhDT             Create new
*/
@Entity
@Table(name = "Reservation")
public class ReservationEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    @SerializedName("reservation_id")
    @JsonProperty("reservation_id")
    private Integer reservationId;

    @Column(name = "user_id")
    @SerializedName("user_id")
    @JsonProperty("user_id")
    private Integer userId;

    @Column(name = "reserved_time")
    @SerializedName("reserved_time")
    @JsonProperty("reserved_time")
    private Date reservedTime;

    @Column(name = "expected_return_date")
    @SerializedName("expected_return_date")
    @JsonProperty("expected_return_date")
    private Date expectedReturnDate;

    @Column(name = "returned_date")
    @SerializedName("returned_date")
    @JsonProperty("returned_date")
    private Date returnedDate;

    @Column(name = "total_fee")
    @SerializedName("total_fee")
    @JsonProperty("total_fee")
    private Double totalFee;

    @Column(name = "created_time")
    @SerializedName("created_time")
    @JsonProperty("created_time")
    private Date createdTime;
    
    @Column(name = "status")
    @SerializedName("status")
    @JsonProperty("status")
    private Integer status;
   
    @ManyToMany
    @JoinColumn(name = "book_item_id")
    @SerializedName("book_item_entities")
    @JsonProperty("book_item_entities")
    private Set<BookItemEntity> bookItemEntities;

	@ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
	@JsonProperty("user_entity")
    private UserEntity userEntity;

    public Integer getReservationId() {
        return reservationId;
    }

    public void setReservationId(Integer reservationId) {
        this.reservationId = reservationId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getReservedTime() {
        return reservedTime;
    }

    public void setReservedTime(Date reservedTime) {
        this.reservedTime = reservedTime;
    }

    public Date getExpectedReturnDate() {
        return expectedReturnDate;
    }

    public void setExpectedReturnDate(Date expectedReturnDate) {
        this.expectedReturnDate = expectedReturnDate;
    }

    public Date getReturnedDate() {
        return returnedDate;
    }

    public void setReturnedDate(Date returnedDate) {
        this.returnedDate = returnedDate;
    }

    public Double getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(Double totalFee) {
        this.totalFee = totalFee;
    }

    public LocalDateTime getCreatedTime() {
        return LocalDateTime.now();
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }
    
    public ReservationStatus getStatus() {
		return ReservationStatus.parse(this.status);
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public void setStatus(ReservationStatus status) {
		this.status = status.value();
	}

    public ReservationEntity(Integer reservationId, Integer userId, Date reservedTime, Date expectedReturnDate, Date returnedDate,
            Double totalFee, Date createdTime, Integer status) {
        super();
        this.reservationId = reservationId;
        this.userId = userId;
        this.reservedTime = reservedTime;
        this.expectedReturnDate = expectedReturnDate;
        this.returnedDate = returnedDate;
        this.totalFee = totalFee;
        this.createdTime = createdTime;
        this.status = status;
    }

    public ReservationEntity() {
        super();
    }

}

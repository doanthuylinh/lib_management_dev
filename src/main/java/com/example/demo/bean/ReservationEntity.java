/////////////////////////////////////////////////////////////////////////////
//
// © 2021 IDTU-CS3332IRFA-21TSP
//
/////////////////////////////////////////////////////////////////////////////

package com.example.demo.bean;

import java.io.Serializable;
import java.time.*;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
    private Integer reservationId;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "reserved_time")
    private LocalDateTime reservedTime;

    @Column(name = "expected_return_date")
    private LocalDateTime expectedReturnDate;

    @Column(name = "returned_date")
    private LocalDateTime returnedDate;

    @Column(name = "total_fee")
    private Double totalFee;

    @Column(name = "created_time")
    private LocalDateTime createdTime;
    
    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
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

    public LocalDateTime getReservedTime() {
        return reservedTime;
    }

    public void setReservedTime(LocalDateTime reservedTime) {
        this.reservedTime = reservedTime;
    }

    public LocalDateTime getExpectedReturnDate() {
        return expectedReturnDate;
    }

    public void setExpectedReturnDate(LocalDateTime expectedReturnDate) {
        this.expectedReturnDate = expectedReturnDate;
    }

    public LocalDateTime getReturnedDate() {
        return returnedDate;
    }

    public void setReturnedDate(LocalDateTime returnedDate) {
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

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public ReservationEntity(Integer reservationId, Integer userId, LocalDateTime reservedTime, LocalDateTime expectedReturnDate, LocalDateTime returnedDate,
            Double totalFee, LocalDateTime createdTime) {
        super();
        this.reservationId = reservationId;
        this.userId = userId;
        this.reservedTime = reservedTime;
        this.expectedReturnDate = expectedReturnDate;
        this.returnedDate = returnedDate;
        this.totalFee = totalFee;
        this.createdTime = createdTime;
    }

    public ReservationEntity() {
        super();
    }

}
